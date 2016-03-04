<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>asd</title>
	</head>
	<body>
		
		<% for(int i = 0; i < 10; i++){ %>
		Számol  <%=i %> <br/>
		<%}%>
		
		<c:forEach begin="0" end="10" var="i">
			Számol <c:out value="${i}" /> <br />
		</c:forEach>
		
	</body>
</html>
