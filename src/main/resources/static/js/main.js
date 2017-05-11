function register() {
	document.getElementById("login").style.display = "none";
	document.getElementById("register").style.display = "inline";
}
function login() {
	document.getElementById("login").style.display = "inline";
	document.getElementById("register").style.display = "none";
}

let loginType = 0;
function changeType(i) {
	loginType = i;
    if (i == 0) {
        $("#loginType").val = "VISITOR";
    }
    else if (i == 1) {
        $("#loginType").val = "GUIDE";
    }
}

$("#login").click(function () {
	$.ajax({
		url: "/sessions/" + (loginType === 0?"guide":"customer"),
		type: 'POST',
		data: JSON.stringify({
			type: $("input[name='type'][checked]").val(),
			username: $("#username").val(),
			password: $("#password").val()
		}),
		contentType: "application/json",
		context: document.body,
		success: function(data, statusText, xhr){
			if (data.code == 200){
				alert("登陆成功！");
			}else alert(data.message);
		}
	});
});

function change(i) {
    loginType = i;
}

$("#register").click(function () {
    $.ajax({
        url: "/accounts",
        type: 'POST',
        data: JSON.stringify({
            type: loginType,
            name: $("#name").val(),
            username: $("#username").val(),
            password: $("#password").val()
        }),
        contentType: "application/json",
        context: document.body,
        success: function(data, statusText, xhr){
            if (data.code == 200){
                alert("注册成功！");
            }else alert(data.message);
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
    var obj = {
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
                cityId: obj.jCity,
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
