<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.mchange.v1.db.sql.CBPCursor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/JavawebProject/WebContent/WEB-INF/pages/jquery-3.2.1.js"></script>
</head>
<body>
	<center>
	<c:if test="${param.title!=null }">
		您已经将${param.title}放入购物车中
		<br><br>
	</c:if>
	<c:if test="${!empty sessionScope.ShoppingCart}">
		您的购物车中有${sessionScope.ShoppingCart.bookNumber}本书,<a href="bookServlet?method=forwardPage&page=cart">查看购物车</a>
	</c:if>
		<br><br>
			<form action="bookServlet?method=getBooks" method="post">
			Price:<input type="text" size="1" name="minPrice"/> - 
				  <input type="text" size="1" name="maxPrice"/>
				  <input type="submit" value="Submit"/>
			</form>
			<br>
			<table cellpadding="10">
				<c:forEach items="${bookpage.list }" var="book">
					<tr>
						<td>
							<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo }&minPrice=${cb.minPrice}&maxPrice=${cb.maxPrice}&id=${book.book_id}">${book.title }</a>
							<br>
							${book.author }
						</td>
						<td>${book.price}</td>
						<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.book_id}&title=${book.title}">加入购物车</a></td>
					</tr>
				</c:forEach>
			</table>
			<br><br>
			共${bookpage.totalPageNumber }页
			&nbsp;&nbsp;
			当前第${bookpage.pageNo }页
			&nbsp;&nbsp;
			<c:if test="${bookpage.hasPrev }">
				<a href="bookServlet?method=getBooks&pageNo=1&minPrice=${cb.minPrice}&maxPrice=${cb.maxPrice}">首页</a>
				&nbsp;&nbsp;
				<a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage }&minPrice=${cb.minPrice}&maxPrice=${cb.maxPrice}">上一页</a>
			</c:if>
			&nbsp;&nbsp;
			<c:if test="${bookpage.hasNext }">
				<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber }&minPrice=${cb.minPrice}&maxPrice=${cb.maxPrice}">末页</a>
				&nbsp;&nbsp;
				<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage }&minPrice=${cb.minPrice}&maxPrice=${cb.maxPrice}">下一页</a>
			</c:if>
			&nbsp;&nbsp;
			<a href="users.jsp">查看记录</a>
	</center>
</body>
</html>