<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
	<center>
		<br><br>
		Title:${book.title}
		<br><br>
		Author:${book.author}
		<br><br>
		Price:${book.price }
		<br><br>
		PublishingDate:${book.publishing_date }
		<br><br>
		Remark:${book.remark }
		<br><br>
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">继续购物</a>
	</center>
</body>
</html>