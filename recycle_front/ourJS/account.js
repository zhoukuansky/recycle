var token = getCookie('token')

var personVue = new Vue({
	el:'#edit-profile',
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