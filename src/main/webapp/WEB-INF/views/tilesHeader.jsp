<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <ul>
        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                <li><a href="/user/login">로그인</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/user/logout">로그아웃</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="/">Home</a></li>
        <li><a href="/board/list">리스트</a></li>
        <li><a href="/user/join">회원가입</a></li>
        <c:if test="${not empty sessionScope.loginUser}">
            <li><a href="/board/writeMod">글쓰기</a></li>
            <li><a href="/user/profile">프로필</a></li>
        </c:if>
    </ul>
</header>