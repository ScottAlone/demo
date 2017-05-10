function register() {
	document.getElementById("login").style.display = "none";
	document.getElementById("register").style.display = "inline";
}
function login() {
	document.getElementById("login").style.display = "inline";
	document.getElementById("register").style.display = "none";
}

var loginType = 0;
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


// var loginType = 0;
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
    $.ajax({
        url: "/getCounty?parentId=" + ele.value,
        type: 'GET',
        context: document.body,
        success: function (data, statusText, xhr) {
            $("#counties").text("");
            for (let i = 0; i < data.data.length; i++) {
                let text = "<li onclick='changeCity(this)' value=\"" + data.data[i].city_id + "\"/li>";
                let element = $(text).text(data.data[i].name);
                $("#counties").append(element);
            }
            $("#counties").css("max-height", "200px");
            $("#counties").css("overflow-y", "auto");
        }
    });
}
let cityId = 0;
function changeCity(obj) {
    $("#jCity").val(obj.innerText);
    cityId = obj.value;
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
                let text = "<li onmouseover='query(this)' value=\"" + data.data[i].city_id + "\"/li>";
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
        "jCity": cityId,
        "jMan": $("#jMan").val(),
        "jWoman": $("#jWoman").val(),
        "jChild": $("#jChild").val(),
        "jType": $("input:radio:checked").val(),
        "jLow": $("#jLow").val(),
        "jHigh": $("#jHigh").val(),
        "jStart": $("#jStart").val(),
        "jEnd": $("#jEnd").val(),
        "jTags": $("#jTags").children('label'),
        "jOtherTags": $("#jOtherTags").val(),
        "jPrice": $("#jPrice").val(),
        "jUsername": "x",
        "jDue": $("#jDue").val(),
    };

    function checkValid(obj) {
        for (let attr in obj){
            let location = $("<a id='a" + attr +"' href='#" + attr + "' style='display: none'></a>");
            $("#page-wrapper").append(location);
            // alert(attr + ":" + obj[attr] + "--" + String(obj[attr] == undefined || obj[attr] == ""));
            if (obj[attr] == undefined || obj[attr] == ""){
                $("#" + attr).attr("placeholder", "Required Area");
                document.getElementById("a" + attr).click();
                $("#wrong" + attr).text("Invalid data type")
                return false;
            }else $("#wrong" + attr).text("")
        }
        if (parseInt(obj.jLow) >= parseInt(obj.jHigh)){
            document.getElementById("ajLow").click();
            $("#wrongjLow").text("Low price should be lower than high price");
            return false;
        }else $("#wrongjLow").text("");
        if (parseInt(obj.jLow) >= 2147483647 || parseInt(obj.jHigh) >= 2147483647){
            document.getElementById("ajLow").click();
            $("#wrongjLow").text("Budget too large");
            return false;
        }else if (parseInt(obj.jLow) <= 0 || parseInt(obj.jHigh) <= 0){
            document.getElementById("ajLow").click();
            $("#wrongjLow").text("Budget should not be negative");
        }

        let a = obj.jStart.split("-");
        let b = obj.jEnd.split("-");
        let ya = parseInt(a[0]);
        let yb = parseInt(b[0]);
        let ma = parseInt(a[1]);
        let mb = parseInt(b[1]);
        let da = parseInt(a[2]);
        let db = parseInt(b[2]);

        let flag = false;
        if (ya <= yb) {
            if (ma <= mb) {
                if (da <= db) {
                    flag = true;
                    $("#wrongjStart").text("");
                }
            }
        }
        if (!flag){
            document.getElementById("ajStart").click();
            $("#wrongjStart").text("End date should be earlier than Start date");
            return false;
        }

        if (parseInt(obj.jPrice) >= 2147483647){
            document.getElementById("ajPrice").click();
            $("#wrongjPrice").text("Price too large");
            return false;
        }else if (parseInt(obj.jPrice) <= 0){
            document.getElementById("ajPrice").click();
            $("#wrongjPrice").text("Price should not be negative");
            return false
        }else $("#wrongjPrice").text("");

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
                others: $("#jOther").val(),
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