const listElem = document.querySelector('#list');
const pagingElem = document.querySelector('#paging');

//() page(DTO)값 보내주기
function getListAjax(page = 1) {
    fetch('fav?page=' + page)
        .then(res => res.json())
        .then(myJson => {
            console.log(myJson);
            makeView(myJson.list);
        //  favlist의 list 키 값 가져오기
            makePaging(myJson.maxPageVal, page);
        });
}

// 페이징 처리하기
function makePaging(maxPageVal, selectedPage) {
    pagingElem.innerHTML = '';
    // var 사용 X
    for(let i=1; i<=maxPageVal; i++) {
        const span = document.createElement('span');
        // 누른 페이지에서는 이벤트 발동이 되지 않도록 설정
        if(selectedPage === i) {
            span.classList.add('selected');
        } else {
            span.classList.add('pointer');
            span.addEventListener('click', function () {
                getListAjax(i);
            })
        }
        span.innerText = i;
        pagingElem.append(span);
    }
}

// 리스트 view 만들기
function makeView(data) {
    listElem.innerHTML = ''; // 값이 있다면 지우기
    const table = document.createElement('table');
    listElem.append(table);
    // `` 은 엔터 값도 들어갈 수 있음 '' 아님 주의
    table.innerHTML = `
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성일시</th>
        </tr>
    `;

    data.forEach(item => {
        const tr = document.createElement('tr');
        table.append(tr);

        tr.classList.add('record');
        tr.addEventListener('click', () => {
            moveToDetail(item.iboard);
        });

        let imgSrc = '/res/img/noprofile.jpg';
        if(item.profilImg != null) {
            imgSrc = `/img/${item.iuser}/${item.profilImg}`; // ${}값을 넣으려면 `` 사용
        }

        tr.innerHTML = `
            <td>${item.iboard}</td>
            <td>${item.title}</td>
            <td>${item.writerNm}<img src="${imgSrc}"></td>
            <td>${item.regdt}</td>
        `;
    });
}

function moveToDetail(iboard) {
    location.href = '/board/detail?iboard=' + iboard;
}

getListAjax();
