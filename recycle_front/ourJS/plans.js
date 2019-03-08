var token = getCookie('token')

var ob = new Vue({
	el: '#content',
	data: {
		orderList1: [],//最近三个完成的待评分的订单
		orderList2: [{ id: "" }],
		orderList3: [],//正在处理评分的订单
		orderList4: [{ grade: "" }],
		rubList: [],
		gradeInfo: "",
		grade: "",
	},
	methods: {
		getRubType: function (rub_id) {
			var type = "";
			for (var i = 0; i < this.rubList.length; i++) {
				if (rub_id == this.rubList[i].id) {
					type = this.rubList[i].rubbis_type
				}
			}
			return type;
		},
		getRubWeight: function (rub_id, orderMoneny) {
			var weight = "";
			for (var i = 0; i < this.rubList.length; i++) {
				if (rub_id == this.rubList[i].id) {
					weight = orderMoneny / this.rubList[i].rub_price
				}
			}
			return weight + 'kg';
		}
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
	success: function (res) {
		if (res.status == 0) {
			ob.rubList = res.data.rows
			for (var i = 0; i < ob.orderList1.length; i++) {
				var rub_id = ob.orderList1[i].rub_id;
				var moneny = ob.orderList1[i].order_moneny;
				ob.orderList1[i].rub_id = ob.getRubType(rub_id);
				ob.orderList1[i]["weight"] = ob.getRubWeight(rub_id, moneny)
			}
			for (var i = 0; i < ob.orderList2.length; i++) {
				var rub_id = ob.orderList2[i].rub_id;
				var moneny = ob.orderList2[i].order_moneny;
				ob.orderList2[i].rub_id = ob.getRubType(rub_id);
				ob.orderList2[i]["weight"] = ob.getRubWeight(rub_id, moneny)
			}
			for (var i = 0; i < ob.orderList3.length; i++) {
				var rub_id = ob.orderList3[i].rub_id;
				var moneny = ob.orderList3[i].order_moneny;
				ob.orderList3[i].rub_id = ob.getRubType(rub_id);
				ob.orderList3[i]["weight"] = ob.getRubWeight(rub_id, moneny)
			}
		}
		if (res.status == 402) {
			alert("token过期，请重新登录");
			window.location.href = "login.html";
		}
	},
	error: function (res) {
		console.log(res);
	}
});

function init() {
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
		success: function (res) {
			if (res.status == 0) {
				ob.orderList1 = res.data.rows
				for (let i = 0; i < ob.orderList1.length; i++) {
					ob.orderList1[i].type = ob.getRubType(ob.orderList1[i].rub_id);
				}
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
		type: "GET",
		url: url + "/odrers/userFindOrdersList",
		async: true,
		headers: {
			token: token
		},
		data: {
			pageNum: 1,
			status: 1  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
		},
		success: function (res) {
			if (res.status == 0) {
				ob.orderList2 = res.data.rows
				for (let i = 0; i < ob.orderList2.length; i++) {
					ob.orderList2[i].type = ob.getRubType(ob.orderList2[i].rub_id);
					ob.orderList2[i].weight = ob.getRubWeight(ob.orderList2[i].rub_id, ob.orderList2[i].order_moneny);
				}
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
		type: "GET",
		url: url + "/odrers/userFindOrdersList",
		async: true,
		headers: {
			token: token
		},
		data: {
			pageNum: 1,
			status: 2  /*1.創建  2.處理 3.處理完成未評分 4.評分完成*/
		},
		success: function (res) {
			if (res.status == 0) {
				ob.orderList3 = res.data.rows;
				for (let i = 0; i < ob.orderList3.length; i++) {
					ob.orderList3[i].type = ob.getRubType(ob.orderList3[i].rub_id);
					ob.orderList3[i].weight = ob.getRubWeight(ob.orderList3[i].rub_id, ob.orderList3[i].order_moneny);
					$.ajax({
						type: "GET",
						url: url + "/superUser/userInformation",
						async: false,
						headers: {
							token: token
						},
						data: {
							id: ob.orderList3[i].user_b_id,
							type: 1,
						},
						success: function (res) {
							ob.orderList3[i].recycle = res.data.tel;
						}
					})
				}
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
		success: function (res) {
			if (res.status == 0) {
				ob.orderList4 = res.data.rows;
				var i = 0;
				console.log(ob.orderList4)
				//var sth;
				for (; i < ob.orderList4.length; i++) {
					ob.orderList4[i].type = ob.getRubType(ob.orderList4[i].rub_id);
					ob.orderList4[i].weight = ob.getRubWeight(ob.orderList4[i].rub_id, ob.orderList4[i].order_moneny);
					$.ajax({
						type: "GET",
						url: url + "/grades/gradesInformation",
						async: false,
						headers: {
							token: token
						},
						data: {
							order_id: ob.orderList4[i].id,
						},
						success: function (res) {
							ob.orderList4[i].oldgrade = res.data.grades_server;
						}
					})
					$.ajax({
						type: "GET",
						url: url + "/superUser/userInformation",
						async: false,
						headers: {
							token: token
						},
						data: {
							id: ob.orderList4[i].user_b_id,
							type: 1,
						},
						success: function (res) {
							ob.orderList4[i].recycle = res.data.tel;
						}
					})
				}
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

function shanchu(e) {
	var index = $(e).attr("value");
	//alert(index)
	$.ajax({
		type: "DELETE",
		url: url + "/odrers/deleteOrders?id=" + ob.orderList2[index].id,
		async: true,
		headers: {
			token: token
		},
		data: {
		},
		success: function (res) {
			if (res.status == 0) {
				//ob.orderList3 = res.data.rows
				alert("删除订单成功！")
				window.location.href = "plans.html";
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

function pingfen(e) {
	var index = $(e).attr("value");
	//alert(index)
	if (ob.grade >= 1 && ob.grade <= 10) {
		$.ajax({
			type: "POST",
			url: url + "/grades/insertGrades",
			async: true,
			headers: {
				token: token
			},
			data: {
				describe: ob.gradeInfo,
				server: ob.grade,
				order_id: ob.orderList1[index].id,
			},
			success: function (res) {
				if (res.status == 0) {
					//ob.orderList3 = res.data.rows
					alert("评分完成！")
					window.location.href = "plans.html";
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
	} else {
		alert("评分不正确，请您重新输入")
		ob.grade = "";
	}

}

setTimeout(init, 10);


//setTimeout("alert(ob.orderList4[0].grade)", 1000);


