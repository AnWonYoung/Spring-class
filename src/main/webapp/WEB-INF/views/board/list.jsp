<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>리스트</h1>

<div>
    <form action="list" id="frm">
        <input type="hidden" name="page" value="${empty param.page == null ? 1 : param.page}">
            <%--    option값 설정 후 풀리지 않게 설정    --%>
        <select name="recordCnt">
            <c:forEach begin="5" end="20" step="5" var="cnt">
               <c:choose>
                   <c:when test="${cnt eq param.recordCnt}">
                       <option selected>${cnt}</option>
                   </c:when>
               </c:choose>
                <option>${cnt}</option>
            </c:forEach>

        </select>
    </form>
</div>

<%-- service 세션 아이디 값 사용 --%>
<div>로그인 아이디 : ${sessionScope.loginUser.uid}</div>
<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일시</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="item">
        <tr class="record" onclick="moveToDetail(${item.iboard});">
            <td>${item.iboard}</td>
            <td>
                <c:choose>
                    <c:when test="${param.searchType eq 1 || param.searchType eq 2}">
                        ${item.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${item.title}
                    </c:otherwise>
                </c:choose>
            <%--    좋아요 기능    --%>
                <c:if test="${not empty sessionScope.loginUser && item.isFav eq 1}">
                    <i class="fas fa-grin-hearts"></i>
                </c:if>
            </td>

            <c:choose>
                <c:when test="${empty item.profileImg}">
                    <c:set var="img" value="/res/img/noprofile.jpg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="img" value="/img/${item.iuser}/${item.profileImg}"/>
                </c:otherwise>
            </c:choose>
            <td>
                <c:choose>
                    <c:when test="${param.searchType eq 4}">
                        ${item.writerNm.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${item.writerNm}
                    </c:otherwise>
                </c:choose>
                <img src="${img}" class="profileImg">
            </td>
            <td>${item.regdt}</td>
        </tr>
    </c:forEach>
</table>
<%-- 페이징 처리 안 풀리도록 쿼리스트링 잘 보내주기 (&recordCnt=${param.recordCnt})--%>
<%-- 검색처리 할 때도 같음 --%>

<div>
    <c:forEach begin="1" end="${requestScope.maxPageVal}" var="page">
        <c:choose>
            <c:when test="${(empty param.page && page eq 1) || param.page eq page}">
                <span class="selected">${page}</span>
            </c:when>
            <c:otherwise>
                <span><a href="list?page=${page}&recordCnt=${param.recordCnt == null ? 5 : param.recordCnt}">${page}</a></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
