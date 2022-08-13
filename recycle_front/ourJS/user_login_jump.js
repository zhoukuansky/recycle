var token = getCookie('token')

var userInfo;

$(document).ready(function () {
	$.ajax({
		type:"get",
		url: url + "/user/userInformation",
		async:true,
		dataType: 'json',
		headers: {
			token : token
		},
		success: function (resData) {
			userInfo = resData.data
			console.log(userInfo)
		}
	});
})
