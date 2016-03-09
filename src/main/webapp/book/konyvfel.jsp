<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="book/book" method="post">
	Szerző: <input type="text" name="author"> <br/>
	Cím: <input type="text" name="title"> <br/>
	Leírás: <input type="text" name="description"> <br/>
	Kiadás éve: <input type="text" name="pubYear"> <br/>
	<input type="submit">
	</form>
</body>
</html>