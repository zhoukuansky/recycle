var token = getCookie('token')

var ob = new Vue({
	el: '#content',
	data: {
		orderList1: [],//最近三个完成的待评分的订单
		orderList2: [],//其他完成的待评分的订单
		orderList3: [],//完成评分的订单
		rubList: []
	},
	methods: {
		getRubType : function (rub_id) {
			var type = "";
			for (var i=0;i<this.rubList.length;i++) {
				if (rub_id == this.rubList[i].id) {
					type = this.rubList[i].rubbis_type
				}
			}
			return type;
		},
		getRubWeight: function (rub_id,orderMoneny) {
			var weight = "";
			for (var i=0;i<this.rubList.length;i++) {
				if (rub_id == this.rubList[i].id) {
					weight = orderMoneny/this.rubList[i].rub_price
				}
			}
			return weight+'kg';
		}
	}
})

//最近三个完成的待评分的订单
$.ajax({
	type: "GET",
	url: url + "/odrers/userFindOrdersList",
	async: true,
	headers: {
		token: token
	},
	data: {
		pageNum: 1,
		pageSize: 3,
		status: 3  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
	},
	success: function(res){
		ob.orderList1 = res.data.rows
	}
})

//其他完成的待评分的订单
$.ajax({
	type: "GET",
	url: url + "/odrers/userFindOrdersList",
	async: true,
	headers: {
		token: token
	},
	data: {
		pageNum: 2,
		status: 3  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
	},
	success: function(res){
		ob.orderList2 = res.data.rows
	}
})

// 已完成评分的订单
$.ajax({
	type: "GET",
	url: url + "/odrers/userFindOrdersList",
	async: true,
	headers: {
		token: token
	},
	data: {
		pageNum: 1,
		status: 4  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
	},
	success: function(res){
		ob.orderList3 = res.data.rows
	}
})

/**
 * 垃圾信息列表，用于获取垃圾类型的相关信息
 */
$.ajax({
	type: "GET",
	url: url + "/rubbish/rubbishList",
	async: true,
	headers: {
		token: token
	},
	data: {},
	success: function(res) {
		ob.rubList = res.data.rows
		for (var i=0;i<ob.orderList1.length;i++) {
			var rub_id = ob.orderList1[i].rub_id;
			var moneny = ob.orderList1[i].order_moneny;
			ob.orderList1[i].rub_id = ob.getRubType(rub_id);
			ob.orderList1[i]["weight"] = ob.getRubWeight(rub_id,moneny)
		}
		for (var i=0;i<ob.orderList2.length;i++) {
			var rub_id = ob.orderList2[i].rub_id;
			var moneny = ob.orderList2[i].order_moneny;
			ob.orderList2[i].rub_id = ob.getRubType(rub_id);
			ob.orderList2[i]["weight"] = ob.getRubWeight(rub_id,moneny)
		}
		for (var i=0;i<ob.orderList3.length;i++) {
			var rub_id = ob.orderList3[i].rub_id;
			var moneny = ob.orderList3[i].order_moneny;
			ob.orderList3[i].rub_id = ob.getRubType(rub_id);
			ob.orderList3[i]["weight"] = ob.getRubWeight(rub_id,moneny)
		}
	}
});

function grading() {
	alert(1)
	console.log($(this))
}