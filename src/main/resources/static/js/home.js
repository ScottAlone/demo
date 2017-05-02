/**
 * Created by Administrator on 2017/5/2.
 */
$("#sub").click(function () {
    $.get("http://localhost:8080/greet?name=" + $("#txt").val()).then(function() {
        location.href = "http://localhost:8080/greet?name=" + $("#txt").val();
    });
});
