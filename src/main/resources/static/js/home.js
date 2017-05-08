function changeToRegister() {
    document.getElementById("login").style.display="none";
    document.getElementById("register").style.display="inline";
}
function changeToLogin() {
	document.getElementById("login").style.display="inline";
	document.getElementById("register").style.display="none";
}

let loginType = 0;
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

$("#loginButton").click(function () {
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
                alert("登陆成功！");
                window.location = "main.html";
            }else alert("登陆失败");
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
            }else alert("注册失败");
        }
    });
});
