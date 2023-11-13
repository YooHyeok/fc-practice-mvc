<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>home</title>
</head>
<body>
    <form method="post" action="/users">
        <div>
            <label for="userId">사용자 아이디</label>
            <input type="text" id="userId" name="userId" placeholder="User ID" class="form-controller">
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="Name" class="form-controller">
        </div>
        <button type="submit">회원가입</button>
    </form>
</body>
</html>