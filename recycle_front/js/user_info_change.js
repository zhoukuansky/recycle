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

//alert(token)
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
            personVue.address=res.data.adress;
            personVue.tel=res.data.tel;
            personVue.email=res.data.email;
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
        adress:personVue.address,
        email:personVue.email,
        name:personVue.name,
    }
    console.log(user)
    user = JSON.stringify(user);
            $.ajax({
                url: url + "/user/updateUser",
                type: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: user,
                headers: {token:token},
                success: function (res) {
                    if (res.status == 0) {
                        //alert(getCookie("token"))
                       alert("修改成功");
                       window.location.href = "user_login_jump.html";
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