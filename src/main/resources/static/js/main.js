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