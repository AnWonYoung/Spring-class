<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h1>로그인</h1>
    <div>${requestScope.errMsg}</div>
    <form action="login" method="post">
        <div>
            <div><input type="text" name="uid" placeholder="id"></div>
            <div><input type="password" name="upw" placeholder="password"></div>
            <div><input type="submit" value="login"></div>
        </div>
    </form>
    <a href="/user/join">회원가입</a>
</body>
</html>
