<%@page import="com.kosta.model.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, td{ border: 1px solid black; border-collapse: collapse;}
td{ padding: 5px;}
tr:first-of-type {
	background-color: skyblue;
}
</style>
</head>
<body>
<h1>직원목록</h1>
<jsp:include page="../common/header.jsp"></jsp:include>

<a href="empInsert.do">신규등록</a>

<table>
 <tr>
   <td>직원번호</td><td>성</td><td>이름</td><td>급여</td>
   <td>부서</td><td>메니져</td><td>전화번호</td><td>커미션</td>
   <td>입사일</td><td>JOB</td><td>이메일</td><td>삭제</td>
 </tr>
 
 <c:forEach items="${emplist }" var="emp" >
 <tr>
   <td>${emp.employee_id }</td><td>${emp.last_name }</td>
   <td><a href="empDetail.do?empid=${emp.employee_id }">
   ${emp.first_name }</a></td>
   <td>${emp.salary }</td>
   <td>${emp.department_id }</td>
   <td>${emp.manager_id }</td>
   <td>${emp.phone_number }</td>
   <td>${emp.commission_pct }</td>
   <td>${emp.hire_date }</td>
   <td>${emp.job_id }</td>
   <td>${emp.email }</td>
   <td><button onclick="call(${emp.employee_id });">삭제</button></td>
 </tr>
</c:forEach>
</table>

<script>
function call(empid){
	location.href="empDelete.do?empid=" + empid ;
}
</script>
</body>
</html>




