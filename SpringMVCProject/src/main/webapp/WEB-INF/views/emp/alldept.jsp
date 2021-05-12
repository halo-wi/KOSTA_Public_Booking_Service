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
</head>
<body>

<ol>
<c:forEach var = "dlist" items="${dlist }" >
<li>
<a href="empByDept.do?department_id=${dlist.department_id }">
${dlist.department_id}</a>------
${dlist.department_name}
</li>
</c:forEach>
</ol>

</body>
</html>