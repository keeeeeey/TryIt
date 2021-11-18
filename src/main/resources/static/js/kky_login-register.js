//login-register
let isValidId = false;
let isValidEmail = false;
let isValidName = false;
let isValidNickName = false;
let isValidPw = false;
let isValidPwCheck = false;
let isValidZipcode = true;
let isValidDetailAddress = true;
let isValidPhonenum = true;

//mypage
let isValidMypageEmail = true;
let isValidMypageNickName = true;

function fn_register() {
	var register = document.register;
	if (isValidId == false) {
		alert("아이디를 잘못 입력하셨습니다.");
		return;
	} else if (isValidEmail == false) {
		alert("이메일을 잘못 입력하셨습니다.");
		return;
	} else if (isValidName == false) {
		alert("이름을 잘못 입력하셨습니다.");
		return;
	} else if (isValidNickName == false) {
		alert("닉네임을 잘못 입력하셨습니다.");
		return;
	} else if (isValidPw == false) {
		alert("비밀번호를 잘못 입력하셨습니다.");
		return;
	} else if (isValidPwCheck == false) {
		alert("비밀번호확인은 필수입니다.");
		return;
	} else if (isValidZipcode == false) {
		alert("주소를 입력해주세요.");
		return;
	} else if (isValidDetailAddress == false) {
		alert("상세주소를 입력해주세요.");
		return;
	} else if (isValidPhonenum == false) {
		alert("핸드폰번호를 잘못 입력하셨습니다.");
		return;
	}
	register.method = "post";
	register.action = "/member/register.do";
	register.submit();
}

function fn_update() {
	var update = document.update;
	if (isValidMypageEmail == false) {
		alert("이메일을 잘못 입력하셨습니다.");
		return;
	} else if (isValidMypageNickName == false) {
		alert("닉네임을 잘못 입력하셨습니다.");
		return;
	} else if (isValidPw == false) {
		alert("비밀번호를 잘못 입력하셨습니다.");
		return;
	} else if (isValidPwCheck == false) {
		alert("비밀번호확인은 필수입니다.");
		return;
	} else if (isValidZipcode == false) {
		alert("주소를 입력해주세요.");
		return;
	} else if (isValidDetailAddress == false) {
		alert("상세주소를 입력해주세요.");
		return;
	} else if (isValidPhonenum == false) {
		alert("핸드폰번호를 잘못 입력하셨습니다.");
		return;
	}
	update.method = "post";
	update.action = "/member/update.do";
	update.submit();
}

function fn_login() {
	//var login = document.login;
	//login.method = "post";
	//login.action = "/member/login.do"
	//login.submit();
	var _id = $("#login_user_id").val();
	var _pw = $("#login_user_pw").val();
	if (_id == "") {
		$("#loginFail").text("아이디를 입력하지 않았습니다.");
		return;
	}
	if (_pw == "") {
		$("#loginFail").text("비밀번호를 입력하지 않았습니다.");
		return;
	}
	$.ajax({
		type: "post",
		asynk: true,
		url: "/member/login.do",
		dataType: "text",
		data: {user_id: _id, user_pw: _pw},
		success: function (data, textStatus) {
			if (data == 'loginFail') {
				$('#loginFail').text("아이디 또는 비밀번호가 잘못 입력 되었습니다.");
			} else if (data == 'loginsuccess') {
				window.location = "/";
			}
		}
	})
}

function fn_deleteAccount() {
	var _id = $("#delete_user_id").val();
	var _pw = $("#delete_user_pw").val();
	if (_pw == "") {
		$("#messageDelete").text("비밀번호를 입력하지 않았습니다.");
	}
	$.ajax({
		type: "post",
		asynk: true,
		url: "/member/delete.do",
		dataType: "text",
		data: {user_id: _id, user_pw: _pw},
		success: function (data, textStatus) {
			if (data == 'deleteSuccess') {
				window.location = "/";
			} else if (data == 'deleteFail') {
				$('#messageDelete').text('비밀번호가 틀립니다.');
			}
		}
	})
}

function fn_sendEmail() {
	var _id = $("#recovery_id").val();
	var _email = $("#recovery_email").val();
	$.ajax({
		type: "POST",
		asynk: true,
		url: "/member/findPw",
		dataType: "text",
		data: {
			user_id: _id,
			user_email: _email
		},
		success: function (data, textStatus) {
			if (data == 'null_id') {
				$("#messageSendId").text("존재하지 않는 아이디입니다.");
			} else if (data == 'null_email') {
				$("#messageSendEmail").text("등록되지 않은 이메일입니다.");
				$("#messageSendId").text("");
			} else if (data == 'successSendEmail') {
				$("#messageSendId").text("");
				$("#messageSendEmail").text("");
				alert("이메일로 임시 비밀번호를 발송하였습니다.");
				document.location.href = "/login-register";
			}
		}
	})
}

function fn_validateID() {
	var idRegExp = /^[a-zA-z0-9]{4,12}$/;
	var _id = $("#user_id").val();
	if (_id == '') {
		$('#messageID').text("아이디 입력은 필수입니다.");
		$("#messageID").css("color", "#ff0000");
		isValidId = false;
		return;
	}
	if (!idRegExp.test(_id)) {
		$('#messageID').text("올바르지 않는 입력방식입니다.");
		$("#messageID").css("color", "#ff0000");
		isValidId = false;
		return;
	}
	$.ajax({
		type: "post",
		asynk: true,
		url: "/member/overlappedID.do",
		dataType: "text",
		data: {user_id: _id},
		success: function (data, textStatus) {
			if (data == 'usable') {
				$('#messageID').text("사용할 수 있는 아이디 입니다.");
				$("#messageID").css("color", "#81c147");
				isValidId = true;
			} else {
				$('#messageID').text("이미 사용중인 아이디 입니다.");
				$("#messageID").css("color", "#ff0000");
				isValidId = false;
			}
		}
	})
}

function fn_validateEmail() {
	var _email = $("#user_email").val();
	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (_email == '') {
		$('#messageEmail').text("이메일 입력은 필수입니다.");
		isValidEmail = false;
		return;
	}
	if (!emailRegExp.test(_email)) {
		$('#messageEmail').text("올바르지 않는 입력방식입니다.");
		isValidEmail = false;
	} else {
		$('#messageEmail').text("");
		isValidEmail = true;
	}
}

function fn_mypageValidateEmail() {
	var _email = $("#mypage_user_email").val();
	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (_email == '') {
		$('#messageMypageEmail').text("이메일 입력은 필수입니다.");
		isValidMypageEmail = false;
		return;
	}
	if (!emailRegExp.test(_email)) {
		$('#messageMypageEmail').text("올바르지 않는 입력방식입니다.");
		isValidMypageEmail = false;
	} else {
		$('#messageMypageEmail').text("");
		isValidMypageEmail = true;
	}
}

function fn_checkName() {
	var _name = $("#user_name").val();
	if (_name == '') {
		$("#messageName").text("이름 입력은 필수입니다.");
		isValidName = false;
	} else {
		$("#messageName").text("");
		isValidName = true;
	}
}

function fn_validateNickName() {
	var _nickname = $("#user_nickname").val();
	if (_nickname == '') {
		$('#messageNickName').text("닉네임 입력은 필수입니다.");
		$("#messageNickName").css("color", "#ff0000");
		isValidNickName = false;
		return;
	}
	$.ajax({
		type: "post",
		asynk: true,
		url: "/member/overlappedNickName.do",
		dataType: "text",
		data: {user_nickname: _nickname},
		success: function (data, textStatus) {
			if (data == 'usable') {
				$('#messageNickName').text("사용할 수 있는 닉네임 입니다.");
				$("#messageNickName").css("color", "#81c147");
				isValidNickName = true;
			} else {
				$('#messageNickName').text("이미 사용중인 닉네임 입니다.");
				$("#messageNickName").css("color", "#ff0000");
				isValidNickName = false;
			}
		}
	})
}

function fn_mypageValidateNickName() {
	var _nickname = $("#mypage_user_nickname").val();
	var _hiddenNickName = $("#hidden_nickname").val();
	if (_nickname == '') {
		$('#messageMypageNickName').text("닉네임 입력은 필수입니다.");
		$("#messageMypageNickName").css("color", "#ff0000");
		isValidMypageNickName = false;
		return;
	}
	if (_nickname == _hiddenNickName) {
		$('#messageMypageNickName').text("현재 사용중인 닉네임 입니다.");
		$("#messageMypageNickName").css("color", "#81c147");
		isValidMypageNickName = true;
		return;
	}
	$.ajax({
		type: "post",
		asynk: true,
		url: "/member/overlappedNickName.do",
		dataType: "text",
		data: {user_nickname: _nickname},
		success: function (data, textStatus) {
			if (data == 'usable') {
				$('#messageMypageNickName').text("사용할 수 있는 닉네임 입니다.");
				$("#messageMypageNickName").css("color", "#81c147");
				isValidMypageNickName = true;
			} else {
				$('#messageMypageNickName').text("이미 사용중인 닉네임 입니다.");
				$("#messageMypageNickName").css("color", "#ff0000");
				isValidMypageNickName = false;
			}
		}
	})
}

function fn_validatePW() {
	var passwordRegExp = /^[a-zA-z0-9]{4,12}$/;
	var _pw = $("#user_pw").val();
	if (_pw == '') {
		$('#messagePw').text("비밀번호 입력은 필수입니다.");
		$("#messagePw").css("color", "#ff0000");
		isValidPw = false;
		return;
	}
	if (!passwordRegExp.test(_pw)) {
		$('#messagePw').text("올바르지 않는 입력방식입니다.");
		$("#messagePw").css("color", "#ff0000");
		isValidPw = false;
	} else {
		$('#messagePw').text("사용가능한 비밀번호 입니다.");
		$("#messagePw").css("color", "#81c147");
		isValidPw = true;
	}
}

function fn_checkPW() {
	var _pw = $("#user_pw").val();
	var _re_pw = $("#user_re_pw").val();
	if (_re_pw == '') {
		$("#messagePwCheck").text("비밀번호확인은 필수입니다.");
		$("#messagePwCheck").css("color", "#ff0000");
		isValidPwCheck = false;
		return
	}
	if (_pw == _re_pw) {
		$("#messagePwCheck").text("비밀번호가 일치합니다.");
		$("#messagePwCheck").css("color", "#81c147");
		isValidPwCheck = true;
	} else {
		$("#messagePwCheck").text("비밀번호가 일치하지 않습니다.");
		$("#messagePwCheck").css("color", "#ff0000");
		isValidPwCheck = false;
	}
}

function fn_mypageValidatePW() {
	var passwordRegExp = /^[a-zA-z0-9]{4,12}$/;
	var _pw = $("#mypage_user_pw").val();
	var _original_pw = $("#hidden_pw").val();
	if (_pw == '') {
		$('#messagePw').text("비밀번호 입력은 필수입니다.");
		$("#messagePw").css("color", "#ff0000");
		isValidPw = false;
		return;
	}
	if (_pw == _original_pw) {
		$('#messagePw').text("기존 비밀번호는 사용할 수 없습니다.");
		$("#messagePw").css("color", "#ff0000");
		isValidPw = false;
		return;
	}
	if (!passwordRegExp.test(_pw)) {
		$('#messagePw').text("올바르지 않는 입력방식입니다.");
		$("#messagePw").css("color", "#ff0000");
		isValidPw = false;
	} else {
		$('#messagePw').text("사용가능한 비밀번호 입니다.");
		$("#messagePw").css("color", "#81c147");
		isValidPw = true;
	}
}

function fn_mypageCheckPW() {
	var _pw = $("#mypage_user_pw").val();
	var _re_pw = $("#mypage_user_re_pw").val();
	if (_re_pw == '') {
		$("#messagePwCheck").text("비밀번호확인은 필수입니다.");
		$("#messagePwCheck").css("color", "#ff0000");
		isValidPwCheck = false;
		return
	}
	if (_pw == _re_pw) {
		$("#messagePwCheck").text("비밀번호가 일치합니다.");
		$("#messagePwCheck").css("color", "#81c147");
		isValidPwCheck = true;
	} else {
		$("#messagePwCheck").text("비밀번호가 일치하지 않습니다.");
		$("#messagePwCheck").css("color", "#ff0000");
		isValidPwCheck = false;
	}
}

function fn_searchPost() {
	daum.postcode.load(function() {
		new daum.Postcode({
	        oncomplete: function(data) {
	   			var addr = '';
	
	   			if (data.userSelectedType == 'R') {
	   				addr = data.roadAddress;
	   			} else {
	   				addr = data.jibunAddress;
	   			}
	   			
	   			document.getElementById('user_zipcode').value = data.zonecode;
	   			document.getElementById('user_address').value = addr;
	   			
	   			document.getElementById('user_detail_address').focus();
	        }
	    }).open();				
	});
}

function fn_checkZipcode() {
	var _zipcode = $("#user_zipcode").val();
	if (_zipcode == '') {
		isValidZipcode = false;
	}
}

function fn_checkDetailAddress() {
	var _detailAddress = $('#user_detail_address').val();
	if (_detailAddress == '') {
		$('#messageDetailAddress').text("상세주소 입력은 필수입니다.");
		isValidDetailAddress = false;
	} else {
		isValidDetailAddress = true;
		$('#messageDetailAddress').text("");
	}
}

function fn_validatePhonenum() {
	var _phonenum = $("#user_phonenum").val();
	var phonenumRegExp = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
	
	if (_phonenum == '') {
		$('#messagePhonenum').text("핸드폰번호 입력은 필수입니다.");
		isValidPhonenum = false;
		return;
	}
	if (!phonenumRegExp.test(_phonenum)) {
		$('#messagePhonenum').text("올바르지 않는 입력방식입니다.");
		isValidPhonenum = false;
	} else {
		isValidPhonenum = true;
		$('#messagePhonenum').text("");
	}
	
}