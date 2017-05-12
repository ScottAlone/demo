function changeToRegister() {
    $("#login").hide();
    $("#register").show();
}
function changeToLogin() {
    $("#login").show();
    $("#register").hide();
}

function isValid(str){
    if(str.value=="") {
        str.placeholder="Required area.";
        str.setAttribute("class", "red");
    } else {
        str.setAttribute("class", "form-control");
    }
    check();
}

function check() {
    let isValid = ($("#reUsername").val() != "" && $("#nickname").val() != "" && $("#rePassword").val() != "");
    if ($("#agree").prop("checked")){
        if (isValid){
            $("#registerButton").removeAttr("disabled");
        }else $("#registerButton").attr("disabled", true);
    }else $("#registerButton").attr("disabled", true);
}

let loginType = 0;
let username;
let userType;
let home = "home.html";
let main = "main.html";
let journey = "journey.html";
let order = "myOrders.html";
let profile = "myOrder.html";
function changeLogin(i) {
    loginType = i;
    if (i == 0){
        $("#typeButton").text("GUIDE");
    }else if(i == 1){
        $("#typeButton").text("VISITOR");
    }
}
let registerType = 0;
function changeRegister(i) {
    registerType = i;
    if (i == 0){
        $("#reTypeButton").text("GUIDE");
    }else if(i == 1){
        $("#reTypeButton").text("VISITOR");
    }
}

function getCookie(key) {
    let test = window.location.href;
    if (test.indexOf(home) != -1){
        return "";
    }
    if (document.cookie.length > 0) {
        let index = document.cookie.indexOf(key + "=");
        let endIndex;
        if (index != -1) {
            index = index + key.length + 1;
            endIndex = document.cookie.indexOf(";",index);
            if (endIndex == -1) {
                endIndex = document.cookie.length;
            }
            return document.cookie.substring(index,endIndex);
        }
    }else window.location = "home.html"
}

function checkCookie() {
    username = getCookie('username');
    userType = getCookie('userType');

    if (username != "" && userType != ""){
        $.ajax({
            url: "/images?username=" + username + "&type=" + userType,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    $("#userImg").attr("src", "data:image/jpg;base64," + data.data);
                }
            }
        });
    }

    if (window.location.href.indexOf(order) != -1){
        let t1 = "Sightseeing tour";
        let t2 = "Business tour";
        let t3 = "Holiday tour";
        let orders;
        let journeyIds1 = [];
        let journeyIds2 = [];
        let journeyIds3 = [];
        $.ajax({
            url: "/orders/" + "guide" + "/status?" + "gUsername=" + "x" + "&status=" + "1" ,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    orders = data.data;
                    for (let i = 0; i < orders.length; i++){
                        journeyIds1.push(orders[i].journeyId)
                    }
                }
            }
        });

        $.ajax({
            url: "journeys/city?cityName=" + "万洲区" ,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    let journeys = data.data;
                    for (let i = 0; i < journeys.length; i++){
                        if (journeyIds1.length >= 1 && contains(journeyIds1, journeys[i].journeyId)){
                            let ele = $("<li class='warning-element' id=\'toa" + i + "\'></li>");
                            let dest = $("<p></p>").text("Destination:  " + journeys[i].cityName);
                            let type = $("<p></p>").text("Type:  " + ((journeys[i].tourType == 1)?t1:(journeys[i].tourType == 2)?t2:t3));
                            let tags = $("<p></p>").text("Tags:  " + journeys[i].tags);
                            let time = $("<p></p>").text("Time:  " + journeys[i].startTime + "  TO  " + journeys[i].endTime);
                            let duration = $("<div class='agile-detail'></div>");
                            let button = $("<button value='" + orders[getIndex(orders, journeys[i].journeyId)].orderId + "\' class='pull-right btn btn-xs btn-primary' onclick='changeToAccept(this)'></button>").text("YES");
                            duration.text("Price:  " + journeys[i].price + "RMB");
                            ele.append(dest).append(type).append(tags).append(time).append(duration);
                            $("#toAccept").append(ele);
                            duration.append(button);
                        }
                    }
                }
            }
        });

        $.ajax({
            url: "/orders/" + "guide" + "/status?" + "gUsername=" + "x" + "&status=" + "2" ,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    let orders = data.data;
                    for (let i = 0; i < orders.length; i++){
                        journeyIds2.push(data.data[i].journeyId)
                    }
                    for (let i = 0; i < journeyIds2.length; i++){
                        $.ajax({
                            url: "journeys/id?journeyId=" + journeyIds2[i],
                            type: 'GET',
                            context: document.body,
                            success: function (data, statusText, xhr) {
                                if (data.code == 200) {
                                    let journey = data.data;
                                    let ele = $("<li class='info-element' id=\'acc" + i + "\'></li>");
                                    let dest = $("<p></p>").text("Destination:  " + journey.cityName);
                                    let type = $("<p></p>").text("Type:  " + ((journey.tourType == 1)?t1:(journey.tourType == 2)?t2:t3));
                                    let tags = $("<p></p>").text("Tags:  " + journey.tags);
                                    let time = $("<p></p>").text("Time:  " + journey.startTime + "  TO  " + journey.endTime);
                                    let duration = $("<div class='agile-detail'></div>");
                                    let button = $("<button value='" + orders[i].orderId + "\' class='pull-right btn btn-xs btn-primary' onclick='changeToFinish(this)'></button>").text("DELIVER");
                                    duration.text("Price:  " + journey.price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#accepted").append(ele);
                                    duration.append(button);
                                }
                            }
                        });
                    }
                }
            }
        });

        $.ajax({
            url: "/orders/" + "guide" + "/status?" + "gUsername=" + "x" + "&status=" + "3" ,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    let orders = data.data;
                    for (let i = 0; i < orders.length; i++){
                        journeyIds3.push(data.data[i].journeyId)
                    }
                    for (let i = 0; i < journeyIds3.length; i++){
                        $.ajax({
                            url: "journeys/id?journeyId=" + journeyIds3[i],
                            type: 'GET',
                            context: document.body,
                            success: function (data, statusText, xhr) {
                                if (data.code == 200) {
                                    let journey = data.data;
                                    let ele = $("<li class='success-element' id=\'fin" + i + "\'></li>");
                                    let dest = $("<p></p>").text("Destination:  " + journey.cityName);
                                    let type = $("<p></p>").text("Type:  " + ((journey.tourType == 1)?t1:(journey.tourType == 2)?t2:t3));
                                    let tags = $("<p></p>").text("Tags:  " + journey.tags);
                                    let time = $("<p></p>").text("Time:  " + journey.startTime + "  TO  " + journey.endTime);
                                    let duration = $("<div class='agile-detail'></div>");
                                    duration.text("Price:  " + orders[i].price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#finished").append(ele);
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}

checkCookie();

$("#loginButton").click(function () {
    let result = $("#wrong");
    $.ajax({
        url: "/sessions/" + (loginType === 0?"guide":"customer"),
        type: 'POST',
        data: JSON.stringify({
            type: loginType,
            username: $("#username").val(),
            password: $("#password").val()
        }),
        contentType: "application/json",
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                result.children("p").remove();
                result.append($("<p style='color: green; text-align: left'></p>").text("登陆成功"));
                let expireDate = new Date();
                expireDate.setDate(expireDate.getDate()+ 1);
                let myCookie1 = "username=" + $("#username").val() + ";expires=" + expireDate.toUTCString() + ";path=/";
                let myCookie2 = "userType=" + loginType + ";expires=" + expireDate.toUTCString() + ";path=/";
                document.cookie = myCookie1;
                document.cookie = myCookie2;
                window.location = "main.html";
            }else {
                result.children("p").remove();
                result.append($("<p style='color: red; text-align: left'></p>").text("用户名或密码错误"));
            }
        }
    });
});
$("#registerButton").click(function () {
    $.ajax({
        url: "/accounts",
        type: 'POST',
        data: JSON.stringify({
            type: registerType,
            name: $("#nickname").val(),
            username: $("#reUsername").val(),
            password: $("#rePassword").val()
        }),
        contentType: "application/json",
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("注册成功！");
            }else alert("注册失败，" + data.message);
        }
    });
});

function query(ele) {
    inStyle(ele);
    $.ajax({
        url: "/getCounty?parentId=" + ele.value,
        type: 'GET',
        context: document.body,
        success: function (data, statusText, xhr) {
            $("#counties").text("");
            for (let i = 0; i < data.data.length; i++) {
                let text = "<li onclick='changeCity(this)' onmouseover='inStyle(this)' onmouseleave='outStyle(this)' value=\"" + data.data[i].city_id + "\" style='text-decoration: underline;'/li>";
                let element = $(text).text(data.data[i].name);
                $("#counties").append(element);
            }
            $("#counties").css("max-height", "200px");
            $("#counties").css("overflow-y", "auto");
        }
    });
}

let cityName = "";
function changeCity(obj) {
    $("#jCity").val(obj.innerText);
    cityName = obj.innerText;
}

function inStyle(obj) {
    obj.style.color = "green";
}

function outStyle(obj) {
    obj.style.color = "grey";
}

$("#jCity").click(function () {
    $.ajax({
        url: "/getCity",
        type: 'GET',
        context: document.body,
        success: function(data, statusText, xhr){
            cities = data.data;
            $("#cities").children("li").remove();
            for (let i = 0; i < data.data.length; i++) {
                let text = "<li onmouseover='query(this)' onmouseleave='outStyle(this)' value=\"" + data.data[i].city_id + "\" style='border: 1px solid gainsboro;'/li>";
                let element = $(text).text(data.data[i].name);
                $("#cities").append(element);
            }
            $("#cities").css("max-height", "200px");
            $("#cities").css("overflow-y", "auto");
        }
    });
    $("#allCities").mouseleave(function () {
        $("#counties").children("li").remove();
        $("#cities").children("li").remove();
    });
});

$("#jSubmit").click(function () {
    let obj = {
        "jName": $("#jName").val(),
        "jNum": $("#jNum").val(),
        "jCity": cityName,
        "jMan": parseInt($("#jMan").val()),
        "jWoman":  parseInt($("#jWoman").val()),
        "jChild":  parseInt($("#jChild").val()),
        "jType": $("input:radio:checked").val(),
        "jLow": parseInt($("#jLow").val()),
        "jHigh": parseInt($("#jHigh").val()),
        "jStart": $("#jStart").val(),
        "jEnd": $("#jEnd").val(),
        "jTags": $("#jTags").children('label'),
        "jOtherTags": $("#jOtherTags").val(),
        "jPrice": parseInt($("#jPrice").val()),
        "jUsername": "x",
        "jDue": $("#jDue").val(),
        "jOther": $("#jOther").val()
    };

    function notNaN(num, name) {
        if (num >= 2147483647 || num <= 0 || isNaN(num)){
            document.getElementById("a" + name).click();
            $("#wrong" + name).text("Invalid data type");
            return false;
        }else {
            $("#wrong" + name).text("");
            return true;
        }
    }

    function checkValid(obj) {
        for (let attr in obj){
            let location = $("<a id='a" + attr +"' href='#" + attr + "' style='display: none'></a>");
            $("#page-wrapper").append(location);
            if (obj[attr] === undefined || obj[attr] === ""){
                document.getElementById("a" + attr).click();
                $("#" + attr).attr("placeholder", "Required Area");
                return false;
            }
        }

        if (cityName === ""){
            document.getElementById("ajCity").click();
            $("#jCity").attr("placeholder", "Please select a destination");
            return false;
        }

        if (!(notNaN(obj.jNum, "jNum") && notNaN(obj.jMan, "jMan") && notNaN(obj.jWoman, "jWoman") &&
            notNaN(obj.jChild, "jChild") && notNaN(obj.jLow, "jLow") && notNaN(obj.jHigh, "jHigh") && notNaN(obj.jPrice, "jPrice"))){
            return false;
        }

        if (obj.jLow >= obj.jHigh){
            document.getElementById("ajLow").click();
            $("#wrongjLow").text("Low price should be lower than high price");
            return false;
        }else $("#wrongjLow").text("");

        let start = obj.jStart.split("-");
        let end = obj.jEnd.split("-");
        let dateStart = new Date(start[0], start[1], start[2]);
        let dateEnd = new Date(end[0], end[1], end[2]);
        let due = obj.jDue.split("-");
        let dateDue = new Date(due[0], due[1], due[2]);

        let now = new Date();
        let nowYear = now.getFullYear();
        let nowMon = now.getMonth();
        let nowDay = now.getDate();
        now =  new Date(nowYear, nowMon, nowDay - 1);
        if (dateStart <= now || dateEnd <= now){
            document.getElementById("ajStart").click();
            $("#wrongjStart").text("Start date was past");
            return false;
        }else $("#wrongjStart").text("");

        if (dateStart > dateEnd){
            document.getElementById("ajStart").click();
            $("#wrongjStart").text("End date should be earlier than Start date");
            return false;
        }else $("#wrongjStart").text("");

        if (dateDue > dateStart){
            document.getElementById("ajDue").click();
            $("#wrongjDue").text("Due date should be earlier than your start time");
            return false;
        }else $("#wrongjDue").text("");

        return true;
    }

    let tags = [];
    for (let i = 0; i < obj.jTags.length; i++){
        if(obj.jTags[i].children[0].checked){
            tags.push(obj.jTags[i].children[0].parentNode.innerText);
        }
    }
    let otherTag = obj.jOtherTags.split("\/");
    otherTag.forEach(t => tags.push(t));

    if (checkValid(obj)){
        $.ajax({
            url: "/journeys",
            type: 'POST',
            data: JSON.stringify({
                journeyName: obj.jName,
                phoneNum: obj.jNum,
                cityName: obj.jCity,
                members: obj.jMan + "/" + obj.jWoman + "/" + obj.jChild,
                tourType: obj.jType,
                lowPrice: obj.jLow,
                highPrice: obj.jHigh,
                startTime: obj.jStart,
                endTime: obj.jEnd,
                tags: tags.toString(),
                others: obj.jOther,
                cUsername: obj.jUsername,
                dueDate: obj.jDue,
                price: obj.jPrice
            }),
            contentType: "application/json",
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    alert("提交成功！");
                }else alert(data.message);
            }
        });
    }
});

$("#jCancel").click(function () {
    if(confirm("确定离开编辑界面？")){
        window.location = "main.html";
    }
});

function changeToAccept(button) {
    button.innerText = "ACCEPTED";
    button.setAttribute("disabled", "disabled");
    $.ajax({
        url: "/orders/accept?orderId=" + button.value,
        type: 'PATCH',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("接受成功");
            }
        }
    });
}

function changeToFinish(button) {
    button.innerText = "FINISHED";
    button.setAttribute("disabled", "disabled");
    $.ajax({
        url: "/orders/deliver?orderId=" + button.value,
        type: 'PATCH',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("提交成功");
            }
        }
    });
}

function contains(arr, obj) {
    let i = arr.length;
    while (i--) {
        if (arr[i] === obj) {
            return true;
        }
    }
    return false;
}

function getIndex(arr, num) {
    for (let i = 0; i < arr.length; i++){
        if (arr[i].journeyId == num){
            return i;
        }
    }
    return -1;
}