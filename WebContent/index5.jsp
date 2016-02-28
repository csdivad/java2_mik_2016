<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<c:forEach var="nev" items="<%=nevek %>" >
	<mik:sayHello><c:out value="${nev}"/></mik:sayHello>
	<br/>
</c:forEach>

<c:forEach var="i" begin="1" end="5">
szarrrr<br/>
</c:forEach>

</body>
</html>