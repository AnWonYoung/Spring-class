<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h1>디테일</h1>
    <div><a href="#" onclick="goBack();">돌아가기</a></div>

    <c:if test="${requestScope.data.iuser eq sessionScope.loginUser.iuser}">
        <div>
            <a href="writeMod?iboard=${param.iboard}">수정</a>
            <a href="delBoard?iboard=${param.iboard}">삭제</a>
        </div>
    </c:if>

    <h1>${requestScope.data.title}</h1>
    <div>글번호 : ${requestScope.data.iboard} <i id="favIcon" class="far fa-grin-hearts pointer"></i></div>
    <div>작성자 : <c:out value="${requestScope.data.writerNm}"/> | 작성일 : ${requestScope.data.regdt}</div>
    <div><c:out value="${requestScope.data.ctnt}"/></div>

    <c:if test="${not empty sessionScope.loginUser}">
    <div>
        <form id="cmtFrm" onsubmit="return false;">
            <input type="text" id="cmt" placeholder="댓글" value="">
            <input type="button" value="댓글달기" onclick="regCmt();">
        </form>
    </div>
    </c:if>
<%--  data-키값${value값}  --%>
    <div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>

    <div id="modal" class="displayNone">
        <div class="modal_content">
            <form id="cmtModFrm" action="#">
                <input type="hidden" id="icmt">
                <input type="text" id="modCmt">
            </form>
            <input type="button" value="댓글 수정" onclick="modAjax();">
            <input type="button" value="취소" onclick="closeModModal();">
        </div>
    </div>