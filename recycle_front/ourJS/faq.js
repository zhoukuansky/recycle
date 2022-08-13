var token = getCookie("token");

var personVue = new Vue({
    el: '#rubbishType',
    data: {
        selected: "1",
        kg: "",
        info: "",
        items: [{ id: "", rubbis_type: "" }],
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
            //alert(getCookie("token"))
            personVue.items = res.data.rows;
            console.log(personVue.items)
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

$("#submit").click(function () {
    console.log(personVue.selected)
    if (personVue.kg == "") {
        alert("重量不能为空");
    } else {
        $.ajax({
            url: url + "/odrers/insertOrders",
            type: "POST",
            dataType: "json",
            data: {
                moneny:personVue.kg*personVue.items[personVue.selected].rub_price,
                order_rub_infor:personVue.info,
                rub_id:personVue.items[personVue.selected].id,
            },
            headers: {
                token: token
            },
            success: function (res) {
                if (res.status == 0) {
                    //alert(getCookie("token"))
                    //personVue.items = res.data.rows;
                    console.log(personVue.items)
                    alert("新建订单成功！");
                    personVue.selected="1";
                    personVue.info="";
                    personVue.kg="";
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