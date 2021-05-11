<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.LocationVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 form{ border : 5px dotted green;}
</style>
</head>
<body>
<h1>부서 신규 등록</h1>


<form action="deptInsert" method="post">
부서번호:<input type="number" name="department_id"><br>
부서이름:<input type="text"  name="department_name"><br>
메니져ID 선택:
<select name="manager_id">

<c:forEach var = "mlist" items="${mlist }" >

<option value="${mlist.manager_id }">
${mlist.fullname }
</option>
</c:forEach>
</select>

LOcationID 선택:
<select name="location_id">

<c:forEach var = "loclist" items="${llist }" >

<option value="${llist.getLocation_id() }">
${llist.getCity() }
</option>
</c:forEach>
</select>


<input type="submit" value="입력하기">
</form>

</body>
</html>



