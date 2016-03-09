<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mik" uri="/WEB-INF/Asd.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String[] nevek = new String[]{ 
	"Béla",
	"István",
	"Dávid"
};
%>

asd

<c:forEach var="nev" items="<%=nevek %>" >
	<mik:sayHello><c:out value="${nev}"/></mik:sayHello>
	<br/>
</c:forEach>

<mik:szorzotabla m="10" n="3"></mik:szorzotabla>

<a href="page?param=include">inc</a>
<a href="page?param=forward">forw</a>
<a href="${pageContext.request.contextPath}/page?param=include">inc</a>
<a href="${pageContext.request.contextPath}/page?param=forward">forw</a>

</body>
</html> 