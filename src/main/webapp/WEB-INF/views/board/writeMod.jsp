<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="writeMod" method="post">
    <input type="hidden" name="iboard" value="0">
    <div><input type="text" name="title" placeholder="제목"></div>
    <div><textarea name="ctnt" placeholder="내용"></textarea></div>
    <div>
        <input type="submit" value="등록하기">
        <input type="reset" value="초기화">
    </div>
</form>