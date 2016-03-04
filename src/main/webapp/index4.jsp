<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mik" uri="/WEB-INF/Asd.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cím</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<% String[] nevek = new String[]{ 
	"Béla",
	"István",
	"Dávid"
};
%>


<c:forEach items="${nevek}" var="nev">
	<mik:sayHello>d</mik:sayHello><br/>
</c:forEach>



</body> 
</html>