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

$('#save').click(function() {
	var pass = personVue.pass;
	if(personVue.pass != "" && personVue.passC != "") {
		var wPattern = /^[a-zA-Z0-9]{5,16}$/;

		if(!wPattern.test(personVue.pass)) {
			{
				personVue.pass = "";
				personVue.passC = "";
				alert("请输入5到16位密码！");
			}
		} else {
			if(personVue.pass != personVue.passC) {
				alert("您输入的两次密码不一致");
				personVue.pass = "";
				personVue.passC = "";
			} else {
				console.log(pass)
				$.ajax({
					type: "POST",
					url: url + "/loginAndRegister/updateRecyclePassword",
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
							window.location.href = 'login.html'
						}
						if(res.status == 402) {
							alert("token过期，请重新登录");
							//				window.location.href = "login.html";
						}
					}
				});
			}
		}

	}

})