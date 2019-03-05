var token = getCookie('token')

var personVue = new Vue({
	el: '#edit-profile',
	data: {
		tel: "",
		address: "",
		email: "",
		name: "",
		pass: "",
		passC: ""
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
			personVue.address = res.data.adress;
			personVue.tel = res.data.tel;
			personVue.email = res.data.email;
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

$('#lll').click(function() {
	var pass = personVue.pass
	console.log(pass)
	$.ajax({
		type: "POST",
		url: url + "/loginAndRegister/updatePassword",
		async: true,
		data: {
			password: pass
		},
		headers: {
			token: token
		},
		success: function(res) {
			if(res.status == 0) {
				alert("成功")
			}
			if(res.status == 402) {
				alert("token过期，请重新登录");
//				window.location.href = "login.html";
			}
		}
	});
})