const listElem = document.querySelector('#list');
function getListAjax() {
    fetch('fav')
        .then(res => res.json())
        .then(myJson => {
            console.log(myJson);
            makeView(myJson);
        });
}

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
