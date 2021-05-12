<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sample1</h1>
	<h1>${title}</h1>

	<form action="sample4.do" method="post">
		아이디 : <input type="text" name="userid" value="abc"> 
		pw : <input type="text" name="userpw" value="abcd"> 
		email : <input type="text" name="email" value="ab@cd">
		 <input type="submit" value="전송">
	</form>
</body>
</html>