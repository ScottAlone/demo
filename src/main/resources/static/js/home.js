function changeToRegister() {
    $("#login").hide();
    $("#register").show();
}
function changeToLogin() {
    $("#login").show();
    $("#register").hide();
}

function isValid(str){
    if(str.value == "") {
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

let home = "home.html";
let main = "main.html";
let journey = "journey.html";
let order = "myOrders.html";
let profile = "myProfile.html";
let attachment = "attachment.html";
let t1 = "Sightseeing tour";
let t2 = "Business tour";
let t3 = "Holiday tour";
let userType = 1;
let username;
let nickname;

function changeLogin(i) {
    userType = i;
    if (i == 0){
        $("#typeButton").text("GUIDE");
    }else if(i == 1){
        $("#typeButton").text("CUSTOMER");
    }
}
let registerType = 1;
function changeRegister(i) {
    registerType = i;
    if (i == 0){
        $("#reTypeButton").text("GUIDE");
    }else if(i == 1){
        $("#reTypeButton").text("CUSTOMER");
    }
}

function getCookie(key) {
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
    let test = window.location.href;
    if (test.indexOf(home) != -1 || test == "http://localhost:8080/"){
        return "";
    }
    username = getCookie('username');
    userType = getCookie('userType');
    nickname = getCookie('nickname');

    if (username != "" && nickname !=""){
        $("#nickname").text(nickname);
        $("#userType").text(userType==0?"GUIDE":"CUSTOMER");
        $.ajax({
            url: "/images?username=" + username + "&type=" + userType,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    $("#userImg").attr("src", "data:image/jpg;base64," + data.data);
                }else $("#userImg").attr("src", "img/examples/2.jpg");
            }
        });
        currentPage();
    }
}

checkCookie();

function currentPage() {
    let location = window.location.href;
    if (location.indexOf(main) != -1){

    }else if (location.indexOf(journey) != -1){
        $("#journeys1").children("li").remove();
        $("#journeys2").children("li").remove();
        $("#journeys3").children("li").remove();
        $.ajax({
            url: "journeys?username=" + username,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    let journeys = data.data;
                    for (let i = 0; i < journeys.length; i++){
                        let journey = journeys[i];
                        let ele = $("<li class='info-element' id=\'j" + i + "\'></li>");
                        let name = $("<p></p>").text("Journey Name:  " + journey.journeyName);
                        let dest = $("<p></p>").text("Destination:  " + journey.cityName);
                        let type = $("<p></p>").text("Type:  " + ((journey.tourType == 1)?t1:(journey.tourType == 2)?t2:t3));
                        let member = $("<p></p>").text("Members:  " + journey.members);
                        let budget = $("<p></p>").text("LowPrice:  " + journey.lowPrice + " ~ " + journey.highPrice);
                        let duration = $("<p></p>").text("Duration:  " + journey.startTime + " TO " + journey.endTime);
                        let tags = $("<p></p>").text("Tags:  " + journey.tags);
                        let others = $("<p></p>").text("Others:  " + journey.others);
                        let price = $("<p></p>").text("Price:  " + journey.price);
                        let dueDate = $("<p></p>").text("DueDate:  " + journey.dueDate);
                        let create = $("<div class='agile-detail'></div>").text("Create:  " + journey.createTime);
                        let button;
                        if (journey.paid == 0){
                            button = $("<button id='pay" + journey.journeyId + "\' value='" + journey.journeyId + "&" + journey.price + "\' class='pull-right btn btn-xs btn-primary' onclick='payPanel(this)'></button>").text("Pay");
                        }else if (journey.paid == -1){
                            button = $("<button id='select" + journey.journeyId + "\' value='" + journey.journeyId + "&" + journey.price + "&" + journey.cityName + "\' class='pull-right btn btn-xs btn-primary' onclick='selectGuide(this)'></button>").text("Select");
                        }
                        ele.append(name).append(dest).append(type).append(member).append(budget).append(duration)
                            .append(tags).append(others).append(dueDate).append(price).append(create);
                        if (journey.paid == 0){
                            $("#journeys3").append(ele);
                            create.append(button);
                        }else if (journey.paid == -1){
                            $("#journeys2").append(ele);
                            create.append(button);
                        }else $("#journeys1").append(ele);
                    }
                }
            }
        });
    }else if (location.indexOf(order) != -1){
        let base = "/orders/" + ((userType==0)?"guide":"customer") + "/status?" + ((userType==0)?"gUsername=":"cUsername=") + username + "&status=";
        let orders;
        let journeyIds1 = [];
        let journeyIds2 = [];
        let journeyIds3 = [];
        $("#toAccept").children("li").remove();
        $("#toDeliver").children("li").remove();
        $("#toFinish").children("li").remove();
        $("#finished").children("li").remove();
        $.ajax({
            url: base + "1" ,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    let orders = data.data;
                    for (let i = 0; i < orders.length; i++){
                        journeyIds1.push(data.data[i].journeyId)
                    }
                    for (let i = 0; i < journeyIds1.length; i++){
                        $.ajax({
                            url: "journeys/id?journeyId=" + journeyIds1[i],
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
                                    let button = $("<button value='" + orders[i].orderId + "\' class='pull-right btn btn-xs btn-primary' onclick='changeToAccept(this)'></button>").text("Accept");
                                    duration.text("Price:  " + journey.price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#toAccept").append(ele);
                                    if (userType == 0){
                                        duration.append(button);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        $.ajax({
            url: base + "2" ,
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
                                    let button = $("<button value='" + orders[i].orderId + "\' class='pull-right btn btn-xs btn-primary' onclick='changeToDeliver(this)'></button>").text("Deliver");
                                    duration.text("Price:  " + journey.price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#toDeliver").append(ele);
                                    if (userType == 0){
                                        duration.append(button);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        $.ajax({
            url: base + "3" ,
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
                                    let button = $("<button value='" + orders[i].orderId + "\' class='pull-right btn btn-xs btn-primary' onclick='changeToFinish(this)'></button>").text("Finish");
                                    duration.text("Price:  " + journey.price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#toFinish").append(ele);
                                    if (userType == 1){
                                        duration.append(button);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        $.ajax({
            url: base + "4" ,
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
                                    let duration = $("<p></p>").text("Price:  " + orders[i].price + "RMB");
                                    ele.append(dest).append(type).append(tags).append(time).append(duration);
                                    $("#finished").append(ele);
                                }
                            }
                        });
                    }
                }
            }
        });
    }else if (location.indexOf(profile) != -1){
        $.ajax({
            url: "/images?username=" + username + "&type=" + userType,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    $("#preImg").attr("src", "data:image/jpg;base64," + data.data);
                }else $("#preImg").attr("src", "img/examples/2.jpg");
            }
        });
    }else if (location.indexOf(attachment) != -1){
        $.ajax({
            url: "/attachments?gUsername=" + username,
            type: 'GET',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    $("#allAttachments").children("li").remove();
                    $("#attachContainer").toggle();
                    let attachments = data.data;
                    for (let i = 0; i < attachments.length; i++){
                        let ele = $("<li class='warning-element' id=\'" + "att" + attachments[i].attachId + "\' value=\'att" + i + "\'></li>");
                        let id = $("<p></p>").text("ID:  " + attachments[i].attachId);
                        let name = $("<p></p>").text("Name:  " + attachments[i].attachName);
                        let token = $("<div class='agile-detail'></div>");
                        let buttonDown = $("<button value='" + attachments[i].attachId + "&" + attachments[i].token + "\' class='pull-right btn btn-xs btn-primary' onclick='download(this)'></button>").text("Download");
                        let buttonDelete = $("<button value='" + attachments[i].attachId + "\' class='pull-right btn btn-xs btn-primary' onclick='del(this)'></button>").text("Delete");
                        token.text("Token:  " + attachments[i].token);
                        ele.append(id).append(name).append(token);
                        $("#allAttachments").append(ele);
                        token.append(buttonDown).append(buttonDelete);
                    }
                }
            }
        });
        if (userType == 1){
            $("#getDiv").hide();
            $("#upDiv").hide();
        }else $("#downDiv").hide();
    }
}

$("#loginButton").click(function () {
    let result = $("#wrong");
    $.ajax({
        url: "/sessions/" + (userType == 0?"guide":"customer"),
        type: 'POST',
        data: JSON.stringify({
            type: userType,
            username: $("#username").val(),
            password: $("#password").val()
        }),
        contentType: "application/json",
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                let account = data.data;
                result.children("p").remove();
                result.append($("<p style='color: green; text-align: left'></p>").text("Login successfully"));
                let expireDate = new Date();
                expireDate.setDate(expireDate.getDate()+ 1);
                let myCookie1 = "username=" + account.username + ";expires=" + expireDate.toUTCString() + ";path=/";
                let myCookie2 = "userType=" + account.type + ";expires=" + expireDate.toUTCString() + ";path=/";
                let myCookie3 = "nickname=" + account.name + ";expires=" + expireDate.toUTCString() + ";path=/";
                document.cookie = myCookie1;
                document.cookie = myCookie2;
                document.cookie = myCookie3;
                window.location = "main.html";
            }else {
                result.children("p").remove();
                result.append($("<p style='color: red; text-align: left'></p>").text("Bad username or password"));
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
                alert("Registered successfully！");
                changeToLogin();
            }else alert("Registered failed，" + data.message);
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
        "jUsername": username,
        "jDue": $("#jDue").val(),
        "jOther": $("#jOther").val()
    };

    function notNaN(num, name) {
        if (num >= 2147483647 || num < 0 || isNaN(num)){
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
            if (obj[attr] == undefined || obj[attr] === ""){
                document.getElementById("a" + attr).click();
                $("#" + attr).attr("placeholder", "Required Area");
                return false;
            }
        }

        if (cityName == ""){
            document.getElementById("ajCity").click();
            $("#jCity").attr("placeholder", "Please select a destination");
            return false;
        }

        if (!(notNaN(obj.jMan, "jMan") && notNaN(obj.jWoman, "jWoman") && notNaN(obj.jChild, "jChild") &&
            notNaN(obj.jLow, "jLow") && notNaN(obj.jHigh, "jHigh") && notNaN(obj.jPrice, "jPrice"))){
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
                    alert("Submit successfully");
                    currentPage();
                    $("#toggleJourneys").attr("class", "btn btn-primary");
                    $("#toggleForm").attr("class", "btn btn-white");
                    $("#journeyForm").hide();
                    $("#journeysDiv").show();
                }else alert(data.message);
            }
        });
    }
});

$("#jCancel").click(function () {
    if(confirm("Sure to leave this page?")){
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
                alert("Accepted successfully");
            }
        }
    });
}

$("#toggleForm").click(function () {
    $("#toggleJourneys").attr("class", "btn btn-white");
    $("#toggleForm").attr("class", "btn btn-primary");
    $("#journeysDiv").hide();
    $("#journeyForm").show();
});

$("#toggleJourneys").click(function () {
    $("#toggleJourneys").attr("class", "btn btn-primary");
    $("#toggleForm").attr("class", "btn btn-white");
    $("#journeyForm").hide();
    $("#journeysDiv").show();
});

function changeToDeliver(button) {
    button.innerText = "Delivered";
    button.setAttribute("disabled", "disabled");
    $.ajax({
        url: "/orders/deliver?orderId=" + button.value,
        type: 'PATCH',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("Delivered successfully");
            }
        }
    });
}

let gUsername = "";
function trcolor(tr, gname) {
    $(tr).css("background-color", "#1cc09f");
    $(tr).css("color", "white");
    gUsername = gname;
}

function show_modal() {
    $('#myModal').modal('show');
    $.ajax({
        url: "/guides?cityName=" + selectArgs[2],
        type: 'GET',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                $("#guides").children("tr").remove();
                let guides = data.data;
                for (let i = 0; i < guides.length; i++){
                    let ele = $("<tr id='tr" + i + "\' onclick=\"trcolor(this, \'" + guides[i].username + "\')\"></tr>");
                    $("#guides").append(ele);
                    let img = $("<td class='client-avatar'><img alt='image' src='data:image/jpg;base64," + guides[i].file + "\'> </td>");
                    let name = $("<td style='font-weight:bolder;' id='td" + i + "\'>" + guides[i].username + "</td>");
                    let stars = "★";
                    while(guides[i].stars - 1 > 0) {
                        stars += "★";
                    }
                    let score = $("<td>" + stars + "</td>");
                    let phone = $("<td>" + guides[i].phoneNum + "</td>");
                    $("#tr" + i).append(img).append(name).append(score).append(phone);
                }
            }
        }
    });
}
$(function () {
    $('#myModal').modal('hide');
});

function show_paymodal() {
    $('#myPayModal').modal('show');
}

let selectArgs;
function selectGuide(button) {
    selectArgs = button.value.split("&");
    show_modal();
}

let payArgs;
function payPanel(button) {
    payArgs = button.value.split("&");
    show_paymodal();
}

$("#confirmSelect").click(function () {
    if (gUsername == ""){
        alert("No guide selected");
        return;
    }else {
        $.ajax({
            url: "/orders",
            type: 'POST',
            data: JSON.stringify({
                journeyId: selectArgs[0],
                cUsername: username,
                gUsername: gUsername,
                price: selectArgs[1]
            }),
            contentType: "application/json",
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    alert("Order created successfully");
                    $.ajax({
                        url: "/journeys/select?journeyId=" + selectArgs[0],
                        type: 'PATCH',
                        data: JSON.stringify({
                            journeyId: selectArgs[0],
                        }),
                        contentType: "application/json",
                        context: document.body,
                        success: function(data, statusText, xhr){
                            if (data.code == 200){
                                $("#select" + selectArgs[0]).text("Selected");
                                $("#select" + selectArgs[0]).attr("disabled", "disable");
                            }else alert(data.message);
                        }
                    });
                }else alert(data.message);
            }
        });
    }
    gUsername = "";
    $('#myModal').modal('hide');
});

$("#confirmPay").click(function () {
    $.ajax({
        url: "/orders/journeyId?journeyId=" + payArgs[0],
        type: 'GET',
        contentType: "application/json",
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                $.ajax({
                    url: "/orders/pay?orderId=" + data.data.orderId,
                    type: 'PATCH',
                    contentType: "application/json",
                    context: document.body,
                    success: function(data, statusText, xhr){
                        if (data.code == 200){
                            alert("Order paid successfully");
                            $.ajax({
                                url: "/journeys/pay?journeyId=" + payArgs[0],
                                type: 'PATCH',
                                context: document.body,
                                success: function(data, statusText, xhr){
                                    if (data.code == 200){
                                        $("#pay" + payArgs[0]).text("Paid");
                                        $("#pay" + payArgs[0]).attr("disabled", "disabled");
                                    }else alert("Error occurred");
                                }
                            });
                        }else alert(data.message);
                    }
                });
            }else alert(data.message);
        }
    });
    $('#myPayModal').modal('hide');
});

function changeToFinish(button) {
    $.ajax({
        url: "/orders/finish?orderId=" + button.value,
        type: 'PATCH',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("Finished successfully");
                button.innerText = "Finished";
                button.setAttribute("disabled", "disabled");
            }
        }
    });
}

$("#freshJourney").click(function () {
    currentPage();
});

$("#freshOrders").click(function () {
    currentPage();
});

let myFile;
let fileName;
let uploaded = false;

function changeImg() {
    uploaded = false;
    let file = this.files[0];
    fileName = file.name;
    if (file.size/1024 >= 1024){
        alert("Size of image should be below 1M");
        return false;
    }
    if(!/image\/\w+/.test(file.type)){
        alert("Image type only");
        return false;
    }
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        $("#preImg").attr("src", this.result);
        myFile = this.result;
        uploaded = true;
    };
    reader = null;
}

$("#uploadImg").bind('change', changeImg);

$("#submitImg").click(function () {
    if (!uploaded){
        alert("Please choose an image");
        return false;
    }
    let d = new FormData();
    d.append("file", myFile);
    d.append("username", username);
    d.append("type", userType);
    let xhr = new XMLHttpRequest();
    xhr.open("post", "/images", true);
    xhr.responseType = "json";
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                let response = xhr.response;
                if (response.code == 200){
                    alert("Upload successfully");
                    $("#userImg").attr("src", myFile);
                }else alert(response.message + "，Upload failed");
            }else alert("Upload failed");
        }
    };
    xhr.send(d);
});

function updateValid(str){
    if(str.value=="") {
        str.placeholder="Required area.";
        str.setAttribute("class", "red");
    } else {
        str.setAttribute("class", "form-control");
    }
}

if (username != undefined){
    $("#gUsername").val(username);
}

if (nickname != undefined){
    $("#new_name").val(nickname);
}

$("#updateUser").click(function () {
    let new_name = $("#new_name").val();
    let old_pwd = $("#old_pwd").val();
    let new_pwd = $("#new_pwd").val();
    if (new_name != "" && old_pwd != "" && new_pwd != ""){
        $.ajax({
            url: "/accounts",
            type: 'PATCH',
            data: JSON.stringify({
                type: userType,
                name: new_name,
                username: username,
                password: old_pwd,
                newPassword: new_pwd
            }),
            contentType: "application/json",
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    alert("Modified successfully！");
                    let expireDate = new Date();
                    expireDate.setDate(expireDate.getDate()+ 1);
                    document.cookie = "nickname=" + new_name + ";expires=" + expireDate.toUTCString() + ";path=/";
                    $("#nickname").text(new_name);
                }else alert(data.message);
            }
        });
    }else alert("Please fill in all the forms");
});

function download(ele) {
    let downArgs = ele.value.split("&");
    $("#attachmentId").val(downArgs[0]);
    $("#token").val(downArgs[1]);
    $("#downloadForm").submit();
}

function del(ele) {
    let id = ele.value;
    if(confirm("Sure to delete this attachment?")){
        $.ajax({
            url: "/attachments/id?attachmentId=" + id,
            type: 'DELETE',
            context: document.body,
            success: function(data, statusText, xhr){
                if (data.code == 200){
                    alert("删除成功");
                    $("#att" + id).remove();
                }else alert(data.message);
            }
        });
    }
}

$("#getAllAttach").click(function () {
    $.ajax({
        url: "/attachments?gUsername=" + username,
        type: 'GET',
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                $("#allAttachments").children("li").remove();
                $("#attachContainer").toggle();
                let attachments = data.data;
                for (let i = 0; i < attachments.length; i++){
                    let ele = $("<li class='warning-element' id=\'" + "att" + attachments[i].attachId + "\' value=\'att" + i + "\'></li>");
                    let id = $("<p></p>").text("ID:  " + attachments[i].attachId);
                    let name = $("<p></p>").text("Name:  " + attachments[i].attachName);
                    let token = $("<div class='agile-detail'></div>");
                    let buttonDown = $("<button value='" + attachments[i].attachId + "&" + attachments[i].token + "\' class='pull-right btn btn-xs btn-primary' onclick='download(this)'></button>").text("Download");
                    let buttonDelete = $("<button value='" + attachments[i].attachId + "\' class='pull-right btn btn-xs btn-primary' onclick='del(this)'></button>").text("Delete");
                   token.text("Token:  " + attachments[i].token);
                    ele.append(id).append(name).append(token);
                    $("#allAttachments").append(ele);
                    token.append(buttonDown).append(buttonDelete);
                }
            }
        }
    });
});
