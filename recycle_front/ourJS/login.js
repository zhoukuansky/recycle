var url="http://114.115.243.22:8080/recycle";
var loginVue = new Vue({
    el: '#login',
    data: {
        selected:"1",
    }
})

$("#submit").click(function () {
    var userName = $.trim($("#username").val());
    var passWord = $.trim($("#password").val());
    console.log(loginVue.selected)

    if (userName != "" && passWord != "") {
        //alert(passWord);
        var uPattern = /^[a-zA-Z0-9]{11,11}$/;
        var wPattern = /^[a-zA-Z0-9]{5,16}$/;
        if (!uPattern.test(userName)) {
            $("#username").val("");
            alert("请输入11位手机号！");
            if (!wPattern.test(passWord)) {
                $("#password").val("");
            }
        } else if (!wPattern.test(passWord)) {
            {
                $("#password").val("");
                $("#password").focus();
                alert("请输入5到16位密码！");
            }
        }
        else {
            $.ajax({
                url: url + "/loginAndRegister/login",
                type: "GET",
                dataType: "json",
                data: {
                    tel: userName,
                    password: passWord,
                    type:loginVue.selected,
                },
                headers: {},
                success: function (res) {
                    if (res.status == 0) {
                        setCookie("token",res.data.token);
                        //alert(getCookie("token"))
                        if(res.data.type==1){
                            window.location.href = "feipin_login_jump.html";
                        }
                        if(res.data.type==2){
                            window.location.href = "user_login_jump.html";
                        }
                        if(res.data.type==3){
                            window.location.href = "super_login_jump.html";
                        }
                    }
                    else {
                        alert("用户名或密码错误,或者角色错误");
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            })
        }
    }
})