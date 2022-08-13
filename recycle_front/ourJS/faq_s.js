var token = getCookie("token");

var personVue = new Vue({
    el: '#person',
    data: {
        type: "",
        price: "",
    }
})


var listVue = new Vue({
    el: '#list',
    data: {
        items: [{
            id:"",
            rub_price: "",
            rubbis_type: "",
        }],
    }
})

$.ajax({
    url: url + "/rubbish/rubbishList",
    type: "GET",
    dataType: "json",
    data: {
    },
    headers: {
        token: token
    },
    success: function (res) {
        if (res.status == 0) {
            console.log(res.data.rows);
            listVue.items = res.data.rows;
        }
        if (res.status == 402) {
            alert("token过期，请重新登录");
            window.location.href = "login.html";
        }
    },
    error: function (res) {
        console.log(res);
    }
})

$('#submit').click(function () {
    console.log(personVue.type)
    if (personVue.type != "" && personVue.price != "") {
        $.ajax({
            url: url + "/rubbish/insertRubbish",
            type: "POST",
            dataType: "json",
            data: {
                type: personVue.type,
                price: personVue.price,
            },
            headers: {
                token: token
            },
            success: function (res) {
                if (res.status == 0) {
                    alert("新建工作垃圾类型");
                    window.location.href = "faq_s.html";
                }
                if (res.status == 402) {
                    alert("token过期，请重新登录");
                    window.location.href = "login.html";
                }
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
})


function shanchu(e){
    var index=$(e).attr("value");
    //console.log(index);
    //console.log(listVue.items[index].id);
    $.ajax({
        url: url + "/rubbish/deleteRubbish?id="+listVue.items[index].id,
        //url: url + "/recycleWorker/deleteWorker",
        type: "DELETE",
        dataType: "json",
        data: {
            id:listVue.items[index].id,
        },
        contentType:"application/x-www-form-urlencoded",
        headers: {
            token: token,
        },
        success: function (res) {
            if (res.status == 0) {
                alert("删除垃圾类型");
                window.location.href = "faq_s.html";
            }
            if (res.status == 402) {
                alert("token过期，请重新登录");
                window.location.href = "login.html";
            }
        },
        error: function (res) {
            console.log(res);
        }
    })
}