var token = getCookie('token')

var ob = new Vue({
	el: '#content',
	data: {
		orderList1: [],
		orderList2: [],
		rubList: []
	},
	methods: {
		getRubType : function () {
			return ;
		}
	}
})

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
		status: 1  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
	},
	success: function(res){
		ob.orderList1 = res.data.rows
	}
})


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
		status: 4  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
	},
	success: function(res){
		ob.orderList2 = res.data.rows
	}
})

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
	}
});

