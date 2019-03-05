var token=getCookie("token");

var personVue = new Vue({
    el: '#rubbishType',
    data: {
      items:[{id:"",rubbis_type:""}],
    }
})

$.ajax({
    url: url + "/rubbish/rubbishList",
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
            personVue.items=res.data.rows;
            console.log(personVue.items)
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