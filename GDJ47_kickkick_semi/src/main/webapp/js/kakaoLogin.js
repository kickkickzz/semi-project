window.Kakao.init("b24fc91f19356994e041edf2455fea67");

function kakaoLogin() {
	window.Kakao.Auth.login({
		scope:'profile_nickname, profile_image, account_email,gender,birthday',
		success: function(authObj) {
			console.log(authObj);
			window.Kakao.API.request({
				url:'/v2/user/me',
				success: res => {
					const kakao_account = res.kakao_account;
					console.log(kakao_account);
				}
			})
		} 
	});
}
