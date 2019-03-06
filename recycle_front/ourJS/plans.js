var token = getCookie('token')

var ob = new Vue({
	el: '#list',
	data: {
		orderList: [],
		rubList: []
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
		pageSize: 3
	},
	success: function(res) {
		ob.orderList = res.data.rows
		console.log(ob.orderList)
		$.ajax({
			type: "GET",
			url: url + "/rubbish/rubbishInformation",
			async: true,
			headers: {
				token: token
			},
			data: {
				id : 2,
			},
			success: function(res){
				
			}
		});
	}
});