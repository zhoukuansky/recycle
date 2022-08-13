var token=getCookie("token");

var personVue = new Vue({
    el: '#info',
    data: {
        id:"",
        tel:"",
        address:"",
        email:"",
        name:"",
    }
})

$.ajax({
    url: url + "/user/userInformation",
    type: "GET",
    dataType: "json",
    data: {
    },
    headers: {
        token:token
    },
    success: function (res) {
        if (res.status == 0) {
            //alert(getCookie("token"))
            personVue.id=res.data.id;
            personVue.address=res.data.address;
            personVue.tel=res.data.tel;
            personVue.email=res.data.mail;
            personVue.name=res.data.name;
            //console.log(personVue)
        }
        if(res.status==402) {
            alert("token过期，请重新登录");
            window.location.href = "login.html";
        }
    },
    error: function (res) {
        console.log(res);
    }
})


$("#submit").click(function () {
    var user={
        id:personVue.id,
        tel:personVue.tel,
        address:personVue.address,
        mail:personVue.email,
        name:personVue.name,
    }
    console.log(user)
    user = JSON.stringify(user);
            $.ajax({
                url: url + "/user/updateRecycleUser",
                type: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: user,
                headers: {token:token},
                success: function (res) {
                    if (res.status == 0) {
                        //alert(getCookie("token"))
                       alert("修改成功");
                       window.location.href = "fepin_login_jump.html";
                    }
                    if(res.status==402) {
                        alert("token过期，请重新登录");
                        window.location.href = "login.html";
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            })
})