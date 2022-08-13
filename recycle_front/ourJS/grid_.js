var token = getCookie('token')

var personVue = new Vue({
	el: '#info',
	data: {
		tel: "",
		address: "",
		email: "",
		name: "",
	}
})

$.ajax({
	url: url + "/user/userInformation",
	type: "GET",
	dataType: "json",
	data: {

	},
	headers: {
		token: token
	},
	success: function(res) {
		if(res.status == 0) {
			//alert(getCookie("token"))
			personVue.id = res.data.id;
			personVue.address = res.data.address;
			personVue.tel = res.data.tel;
			personVue.email = res.data.mail;
			personVue.name = res.data.name;
		}
		if(res.status == 402) {
			alert("token过期，请重新登录");
			window.location.href = "login.html";
		}
	},
	error: function(res) {
		console.log(res);
	}
})

$('#submit').click(function() {
	var app = {
		"address": personVue.address,
		"mail": personVue.email,
		"name": personVue.name,
		"tel": personVue.tel
	};
	app = JSON.stringify(app);
	$.ajax({
		type: "PUT",
		url: url + "/user/updateRecycleUser",
		async: true,
		contentType : 'application/json',
		dataType: 'json',
		data: app,
		headers: {
			token: token
		},
		success: function(res) {
			if(res.status == 0) {
				alert('修改成功')
			}

			if(res.status != 0) {
				alert('修改失败，请重试')
				personVue.address = "";
				personVue.tel = "";
				personVue.email = "";
				personVue.name = "";
			}
		}
	});
})