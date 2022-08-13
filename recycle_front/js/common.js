var url="http://114.115.243.22:8080/recycle";
var token = getCookie('token');

var userVue = new Vue({
	el: '#user',
	data: {
		tel: "",
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
			userVue.tel = res.data.tel;
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