var url="https://zk.zksky.top/recycle";
var registerVue = new Vue({
    el: '#register',
    data: {
        selected:"1",
    }
})

$("#submit").click(function () {
    var userName = $.trim($("#username").val());
    var passWord = $.trim($("#password").val());
    console.log(registerVue.selected)

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
                alert("请输入5到16位密码！且不能有特殊符号");
            }
        }
        else {
            $.ajax({
                url: url + "/loginAndRegister/register",
                type: "POST",
                dataType: "json",
                data: {
                    tel: userName,
                    password: passWord,
                    type:registerVue.selected,
                },
                headers: {},
                success: function (res) {
                    if (res.status == 0) {
                        alert("注册成功");
                        window.location.href = "login.html";
                    }
                    else if(res.status==4){
                        alert("用户已经存在");
                    }
                    else {
                        alert("用户名或密码错误");
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            })
        }
    }
})



