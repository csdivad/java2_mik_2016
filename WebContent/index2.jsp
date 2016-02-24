<%@page import="hu.sol.mik.hw.test.bean.HelloBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%=new HelloBean().getSayHello() %>
<jsp:include page="/helloServlet"></jsp:include>