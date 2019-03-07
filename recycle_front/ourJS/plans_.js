var token = getCookie("token");

var rubbishType=[];

var newOrders = new Vue({
    el: '#newOrders',
    data: {
        items:[
            {order_moneny:"",type:"",kg:"",order_rub_infor:"",order_time_begin:"",}
        ],
    }
})

var doingOrders=new Vue({
    el: '#doingOrders',
    data: {
        items:[
            {order_moneny:"",type:"",kg:"",order_rub_infor:"",order_time_deal:"",}
        ],
    }
})

var finishOrders=new Vue({
    el: '#finishOrders',
    data: {
        items:[
            {order_moneny:"",type:"",kg:"",order_time_finish:"",}
        ],
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
                rubbishType = res.data.rows;
                console.log(rubbishType);
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

function init(){
    $.ajax({
    url: url + "/odrers/newOrdersList",
    type: "GET",
    dataType: "json",
    data: {
        pageNum:1,
        pageSize:3,
    },
    headers: {
        token: token
    },
    success: function (res) {
        if (res.status == 0) {
            newOrders.items = res.data.rows;
            for(let  i=0;i<newOrders.items.length;i++){
                for(let j=0;j<rubbishType.length;j++){
                    if(newOrders.items[i].rub_id==rubbishType[j].id){
                        newOrders.items[i].type=rubbishType[j].rubbis_type;
                        newOrders.items[i].kg=newOrders.items[i].order_moneny/rubbishType[j].rub_price;
                    }
                }
                
            }
            console.log(res.data.rows);
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

$.ajax({
    url: url + "/odrers/recycleFindOrdersList",
    type: "GET",
    dataType: "json",
    data: {
        status:2,
    },
    headers: {
        token: token
    },
    success: function (res) {
        if (res.status == 0) {
            doingOrders.items = res.data.rows;
            for(let  i=0;i<doingOrders.items.length;i++){
                for(let j=0;j<rubbishType.length;j++){
                    if(doingOrders.items[i].rub_id==rubbishType[j].id){
                        doingOrders.items[i].type=rubbishType[j].rubbis_type;
                        doingOrders.items[i].kg=doingOrders.items[i].order_moneny/rubbishType[j].rub_price;
                    }
                }
                
            }
            console.log(res.data.rows);
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

$.ajax({
    url: url + "/odrers/recycleFindOrdersList",
    type: "GET",
    dataType: "json",
    data: {
        status:3,
    },
    headers: {
        token: token
    },
    success: function (res) {
        if (res.status == 0) {
            finishOrders.items = res.data.rows;
            for(let  i=0;i<finishOrders.items.length;i++){
                for(let j=0;j<rubbishType.length;j++){
                    if(finishOrders.items[i].rub_id==rubbishType[j].id){
                        finishOrders.items[i].type=rubbishType[j].rubbis_type;
                        finishOrders.items[i].kg=finishOrders.items[i].order_moneny/rubbishType[j].rub_price;
                    }
                }
                
            }
            console.log(res.data.rows);
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

function jeishou(e){
    var index=$(e).attr("value");
    //alert(index);
    $.ajax({
        url: url + "/odrers/dealOrders?id="+newOrders.items[index].id,
        type: "PUT",
        dataType: "json",
        data: {
            //id:newOrders.items[index].id,
        },
        headers: {
            token: token
        },
        success: function (res) {
            if (res.status == 0) {
                alert("接受订单成功，请安排人员前往处理!");
                window.location.href = "plans_.html";
                //rubbishType = res.data.rows;
                //console.log(rubbishType);
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

function finishOrder(e){
    var index=$(e).attr("value");
    //alert(index);
    $.ajax({
        url: url + "/odrers/finishOrders?id="+doingOrders.items[index].id,
        type: "PUT",
        dataType: "json",
        data: {
            //id:newOrders.items[index].id,
        },
        headers: {
            token: token
        },
        success: function (res) {
            if (res.status == 0) {
                alert("此订单已顺利完成！");
                window.location.href = "plans_.html";
                //rubbishType = res.data.rows;
                //console.log(rubbishType);
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

setTimeout(init,10);

