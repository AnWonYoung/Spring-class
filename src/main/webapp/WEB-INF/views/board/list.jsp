<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>리스트</title>
</head>
<body>
<h1>리스트</h1>
<table>
    <tr>
        <th>글 번호</th>
        <th>글 제목</th>
        <th>글쓴이</th>
        <th>작성일</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="item">
        <tr class="record" onclick="moveToDetail(${item.iboard});">
            <td>${item.iboard}</td>

            <td><!--  검색했을 때 해당 글자만 마크 -->
                <c:choose>
                    <c:when test="${param.searchType eq 4 }">
                        ${item.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${item.writerNm}
                    </c:otherwise>
                </c:choose>
                <img src="${img}" class="profileImg">
            </td>
            <td>${item.regdt}</td>
        </tr>

        <c:choose>
            <c:when test="${empty item.profileImg}">
                <c:set var="img" value="/res/img/noprofile.jpg"/>
            </c:when>
            <c:otherwise>
                <c:set var="img" value="/res/img/user/${items.iuser}/${item.profileImg}"/>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</table>
</body>
</html>
