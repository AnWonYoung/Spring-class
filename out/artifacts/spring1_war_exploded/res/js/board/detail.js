var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');

var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
	var cmtVal = cmtFrmElem.cmt.value;
//  console.log('cmtVal : ' + cmtVal);
//	console.log(cmtFrmElem.dataset.iboard); 
	// 위처럼 .으로 속성값에 접근이 가능
	
	var param = {
		iboard: cmtListElem.dataset.iboard,
		cmt: cmtVal
	};
	regAjax(param);
}

// 서버에 등록해주는 함수
function regAjax(param) {
	const init = {
		method: 'POST',
		body: JSON.stringify(param),
		// json 형태로 날릴 땐 아래 헤더가 필요함
		// new URLSearchParams 형식도 가능, get paramerter 값으로 날아감
		// 대신, @RequestBody는 없어야 함
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};

	fetch('cmt', init)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);

			switch(myJson.result) {
				case 0: //등록 실패
					alert('등록 실패!');
					break;
				case 1: //등록 성공
					cmtFrmElem.cmt.value = '';
					getListAjax();
					break;
			}
		});
}
// 서버에게 댓글 리스트를 달라고 요청하는 함수
// 쿼리스트링으로 json을 날리고 있음 = @RequestBody 필요 X
// 쿼리스트링 수정함! 수정 전 = (cmt?iboard)
function getListAjax() {
	var iboard = cmtListElem.dataset.iboard;
	
	fetch('cmt/' + iboard)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) {
		console.log(myJson);
		
		makeCmtElemList(myJson);
	});
}

// html이 아닌 js로 댓글창 구현하기
// 메모리로 <table></table> << 해당 형식이 생성되도록 설정한 것
function makeCmtElemList(data) {
	
//	case 1: 에서 getListAjax();를 호출하며 생기는 중복 테이블을 없애기

//	innerHTML = 태그를 감싸고 찍는 것(js가 실행되지 않아서 보안에 우수함)
//	innerText = 태그까지 모두 찍는 것
//	append = 뒤로 찍혀감 prepend = 앞으로 찍혀감
//	appendChild = '값'과 같이 문자열로 찍히지 않고 객체로만 찍힘
//	ex) var div = document.createElement('값');
	cmtListElem.innerHTML = '';
	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');
//	<th>내용</th>과 같음
	thElemCtnt.innerText = '내용';
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';
//  Title 안쪽으로 소속시킨 것	
	trElemTitle.append(thElemCtnt);
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);
//	모두를 table 안으로 소속시킨 것
	tableElem.append(trElemTitle);
	cmtListElem.append(tableElem);
	
//	세션에 박힌 로그인 값을 가져와서 삭제 및 수정 구현, dataset으로 사용할 때는 대문자 불가능
	var loginUserPk = cmtListElem.dataset.loginUserPk;
	
// data는 makeCmtElemList(data)의 data값, 반복문은 함수라는 인자값을 가지며 item을 계속해서 넣어준다는 의미	
	data.forEach(function(item) {
		var trElemCtnt = document.createElement('tr');
		var tdElem1 = document.createElement('td');
		var tdElem2 = document.createElement('td');
		var tdElem3 = document.createElement('td');
		var tdElem4 = document.createElement('td');
// innerText, append 모두 값을 넣을 수 있음		
		tdElem1.append(item.cmt);
		tdElem2.append(item.writerNm);
		tdElem3.append(item.regdate);
		
		if(parseInt(loginUserPk) === item.iuser) {
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');
			
//			삭제=ajax로 날릴 것, 안에 적지 않고 따로 함수를 생성 해 안에서 호출만 해두기 
//			confirm = boolean값으로 취소 flase, 확인은 true
			delBtn.addEventListener('click', function() {
				if(confirm('삭제하시겠습니까?')) {
					delAjax(item.icmt);
					}
				});
//			댓글 수정 모달창 띄우기				
			modBtn.addEventListener('click', function() {
				openModModal(item);
			});
			
			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';
			
			tdElem4.append(delBtn);
			tdElem4.append(modBtn);
		}
		
		trElemCtnt.append(tdElem1);
		trElemCtnt.append(tdElem2);
		trElemCtnt.append(tdElem3);
		trElemCtnt.append(tdElem4);
		
		tableElem.append(trElemCtnt);
	});
}
// 삭제하는 함수
// 같은 주소값으로 method에 따라서 CRUD로 날아감
function delAjax(icmt) {
	fetch('cmt/' + icmt, {method: 'DELETE'})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		console.log(data);
		switch(data.result) {
			case 0:
				alert('댓글 삭제 실패');
			break;
			case 1:
				getListAjax();
			break;
		}
	});
}

// 모달창을 열기 위해서 class에 빈칸을 줌
// {값을 받고싶은 멤버 필드를 기입}
function openModModal({icmt, cmt}) {
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	cmtModModalElem.className = '';

	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.modCmt.value = cmt;
}
// 모달창 닫기 displayNone
function closeModModal() {
	cmtModModalElem.className = 'displayNone';
}

function modAjax() {
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt: cmtModFrmElem.icmt.value,
		cmt: cmtModFrmElem.modCmt.value
	}
	
	const init = {
		method: 'PUT',
		body: JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	
	fetch('cmt', init)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) {
		closeModModal();
		console.log(myJson);
		getListAjax();
	}) 
		
}

// 좋아요 구현
// ins, del을 따로 나눠서 복잡도가 높아짐
var favIconElem = document.querySelector('#favIcon');
// 이벤트 처리
favIconElem.addEventListener('click', function () {
	if(favIconElem.classList.contains('fas')) { // fas가 있다면 좋아요 처리 X > O 처리
		insFavAjax();
	}else { // O > X 처리
		delFavAjax();
	}
});
// 좋아요 처리
function insFavAjax() {
	const param = {
		iboard: cmtListElem.dataset.iboard
	}

	const init = {
		method : 'POST',
		board : JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	fetch('fav', init)
		.then(function (res) {
			return res.json();
		})
		.then(function (myJson) {
			if(myJson.result === 1) {
				taggleFav(1);
			}
		})
}
// 좋아요 취소
function delFavAjax() {
	const init = {
		method: 'DELETE'
	}

	const iboard = cmtListElem.dataset.iboard;

	fetch('fav?iboard=' + iboard , init)
		.then(function (res) {
			return res.json();
		})
		.then(function (myJson) {
			if(myJson.result === 1) {
				taggleFav(0);
			}
		})

}

// 좋아요 여부 값 가져오기 > cotroller GetMapping /fav와 통신
// 0이 오면 그대로, 1이 넘어오면(좋아요를 누른 것) 색이 바뀌어야 함
function getFavAjax() {
	fetch('fav?iboard=' + cmtListElem.dataset.iboard)
		.then(function (res) {
			return res.json();
		})
		.then(function (myJson) {
			taggleFav(myJson.result);
		});
}

function taggleFav(taggle) {
	switch (taggle) {
		case 0: // 기존에 좋아요를 누른 상태였다면 삭제를 해주는 처리
			favIconElem.classList.remove('fas');
			favIconElem.classList.add('far'); // 위가 아닐 시 그대로 far인 상태
			break;
		case 1: // 좋아요를 눌렀을 때는 위와 반대로 처리
			favIconElem.classList.remove('far');
			favIconElem.classList.add('fas');
			break;
	}
}

getListAjax(); // 해당 파일이 import되자마자 함수를 1회 호출하는 것
getFavAjax(); // 바로 호출