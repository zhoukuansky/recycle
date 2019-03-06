var token = getCookie("token");

var personVue = new Vue({
    el: '#person',
    data: {
        name: "",
        tel: "",
    }
})


var listVue = new Vue({
    el: '#list',
    data: {
        items: [{
            id:"",
            user_b_w_name: "",
            user_b_w_tel: "",
        }],
    }
})

$.ajax({
    url: url + "/recycleWorker/RecycleWorkerList",
    type: "GET",
    dataType: "json",
    data: {
    },
    headers: {
        token: token
    },
    success: function (res) {
        if (res.status == 0) {
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
    if (personVue.name != "" && personVue.tel != "") {
        $.ajax({
            url: url + "/recycleWorker/insertRecycleWorker",
            type: "POST",
            dataType: "json",
            data: {
                name: personVue.name,
                tel: personVue.tel,
            },
            headers: {
                token: token
            },
            success: function (res) {
                if (res.status == 0) {
                    alert("新建工作人员成功");
                    window.location.href = "faq_.html";
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
    console.log(listVue.items[index].id);
    $.ajax({
        url: url + "/recycleWorker/deleteWorker",
        type: "DELETE",
        dataType: "json",
        data: {
            id:listVue.items[index].id,
        },
        headers: {
            token: token
        },
        success: function (res) {
            if (res.status == 0) {
                alert("删除工作人员成功");
                window.location.href = "faq_.html";
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