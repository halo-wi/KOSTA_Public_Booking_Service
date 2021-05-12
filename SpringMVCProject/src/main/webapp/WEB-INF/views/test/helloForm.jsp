<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${title}</h1>
<h1>${dept}</h1>

<form action="hello.do" method="post">

아이디 : <input type="text" name ="userid" value="abcd">
pw : <input type="text" name ="userpw" value="abcd">
email : <input type="text" name ="email" value="ab@cd">
<input type="submit" value="전송" onclick="submit">
</form>
</body>
</html>