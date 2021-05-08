<%@page import="com.kosta.model.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!-- 표준액션 컴파일후 합친다-->
<jsp:include page="../common/header.jsp"></jsp:include>

<a href="empinsert.do">신규등록</a>
<br><br>
<ul>
  <li>문자값:${myname }</li>
  <li>숫자값:<%=request.getAttribute("myscore") %></li>
  <li>emp객체:${ myemp}</li>
  <li>info객체:${ info}</li>
</ul>


<table>
 <tr>
   <td>직원번호</td><td>성</td><td>이름</td><td>급여</td>
   <td>부서</td><td>메니져</td><td>전화번호</td><td>커미션</td>
   <td>입사일</td><td>JOB</td><td>이메일</td><td>삭제</td>
 </tr>
<c:forEach var = "emp"  items="${emplist }">
<c:url value="empDetail"  var="empD">
<c:param name="empid" value="${emp.employee_id}"></c:param>
</c:url>
 <tr>
   <td><a href="${empD }">${emp.employee_id}</a></td>
   <td><a href="${empD }">${fn:toUpperCase(emp.last_name)}</a></td>
   <td><a href="${empD }">${fn:toLowerCase(emp.first_name)}</a></td>
   <td>
   <fmt:formatNumber type="currency" currencySymbol="$" groupingUsed="true" value="${emp.salary}"/>
   </td>
   <td>${emp.department_id}</td>
   <td>${emp.manager_id}</td>
   <td> ${emp.phone_number}</td>
   <td>${emp.commission_pct}</td>
   <td><fmt:formatDate pattern="YY-MM-dd" value="${emp.hire_date}"/></td>
   <td>${emp.job_id}</td>
   <td>${emp.email}</td>
   <td><button onclick="call(${emp.employee_id});">삭제</button></td>
 </tr>
</c:forEach>
</table>
<!-- 디렉티브액션 합친후 컴파일-->

<c:set var="cPath" value="${pageContext.request.contextPath }"/>
<%-- <%@ include file = "../common/footer.jsp" %>
 --%>[]
<!-- ㅋ머파일 전에 경롤에서 ㅠㅏ일을 찾아서 합친다. -->

<jsp:include page="${cPath }/common/footer.jsp"/>

<script>
function call(empid){
	location.href="empDelete?empid=" + empid ;
}
</script>
</body>
</html>




