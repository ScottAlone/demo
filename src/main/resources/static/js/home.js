function changeToRegister() {

    $("#login").hide();
    $("#register").show();
}
function changeToLogin() {
    $("#login").show();
    $("#register").hide();
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
    var result = $("#wrong");
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
            }else alert("注册失败");
        }
    });
});
