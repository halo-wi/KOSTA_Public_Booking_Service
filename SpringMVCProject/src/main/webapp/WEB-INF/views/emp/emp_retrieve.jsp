<%@page import="com.kosta.model.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
List<EmpVO> elist = (List<EmpVO>)request.getAttribute("empall");
%>
<style>
table,td{border:1px solid black;
border-collapse: collapse;}
td{padding: 5px;}
tr:first-of-type{
	background-color: blue;
}

</style>
</head>
<body>
<h1>부서목록</h1>
<a href="empInsert">신규등록</a>


<table>
	<tr>
		<td>사번</td><td>이름</td><td>성</td><td>이메일</td><td>전화번호</td><td>입사일</td><td>직책</td><td>급여</td><td>인센티브</td><td>매니저</td><td>부서</td>
	</tr>
	<% for(EmpVO emp : elist){%>
	<tr>
		<td><a href="empDetail?empid=<%=emp.getEmployee_id()%>"><%=emp.getEmployee_id()%></a></td>
		<td><%=emp.getLast_name() %></td>
		<td><%=emp.getFirst_name()%></td>
		<td><%=emp.getEmail() %></td>
		<td><%=emp.getPhone_number() %></td>
		<td><%=emp.getHire_date() %></td>
		<td><%=emp.getJob_id() %></td>
		<td><%=emp.getSalary() %></td>
		<td><%=emp.getCommission_pct() %></td>
		<td><%=emp.getManager_id() %></td>
		<td><%=emp.getDepartment_id() %></td>
		
	</tr>
	<%} %>

</table>


</body>
</html>