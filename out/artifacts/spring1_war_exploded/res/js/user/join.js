// 회원가입 체크하는 메소드

var frmElem = document.querySelector('#frm'); // frm은 태그 자체를 의미 ('#아이디 값') 적어주는 것
// frmElem 자식 중 uid 값이 uidElem으로 들어간 것
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;

var btnChkIdElem = frmElem.btnChkId;
// var btnChkIdElem = document.querySelector('#btnChkId'); 위와 동일
var chkUidResultElem = document.querySelector('#chkUidResult');
// div로 묶인 건 document로 접근하기

// js에서 btnChkId로 이벤트를 거는 방법 바인딩 onclick
// btnChkIdElem.onclick = function() { }

// ajax 통신으로 중복체크를 해줄 것
// 요청과 응답은(data가 옮겨가는 과정) 모두 json으로 보내줄 것(ajax는 jsp를 리턴하지 않음)
btnChkIdElem.addEventListener('click', function() {
	idChkAjax(btnChkIdElem.value);
});
// 중복체크를 클릭과 동시에 btnChkIdElem.value가 아래 uid parameter안으로 들어감
function idChkAjax(uid) {
	console.log(uid);
	//chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
	// 창이 아니라 아래에 나타남
	
	// fetch는 미설정 시 해당 주소 get방식으로 날아감
	fetch('uidChk?uid=' + uid)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) {
		console.log(myJson); // << IdChkAjaxServlet의 result값이 발동되면서 myjson객체 생성\
		switch(myJson.result) {
			case 0:
			chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
			break;
			case 1:
			chkUidResultElem.innerText = '이 아이디는 사용할 수 없습니다.';
			break;
		}
	});
}




								// 아이디, 비번, 이름 조건문
function frmChk() {
	// 이상이 생겼을 때만 return값으로 false를 날릴 것
	var uidVal = uidElem.value;
	if(uidVal.length == 0) {
		alert('아이디를 작성해 주세요')
		return false;
	}else if(uidVal.length < 3) {
		alert('아이디는 3자 이상 작성해 주세요')
		return false;
	}
	
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	
	if(upwVal.length < 3) {
		if(upwVal.length == 0) {
			alert('비밀번호를 작성해 주세요')
		}else {
			alert('비밀번호를 4자 이상 적어주세요')
		}
		return false;
	} else if(upwVal !== chkUpwVal) {
		alert('비밀번호를 다시 확인하세요')
		return false;
	}
	
	if(unmElem.value.length < 2) {
		alert('이름은 2자 이상 작성해 주세요')
		return false;
	}
}