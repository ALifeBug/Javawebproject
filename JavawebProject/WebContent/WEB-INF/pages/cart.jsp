<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>在此处插入标题</title>
</head>
<body>
	<center>
		<br><br>
		<div id="bookNumber">您的购物车中共有${sessionScope.ShoppingCart.bookNumber}本书</div>
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title}</td>
					<td>${item.quantity }</td>
					<td>${item.book.price}</td>
					<td><a href="bookServlet?method=remove&id=${item.book.book_id}" class="delete" onclick="if(confirm('确定删除吗?')==false)return false;">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" id="totalMoney">总金额:￥${sessionScope.ShoppingCart.totalMoney }</td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="bookServlet?method=getBooks">继续购物</a>
					&nbsp;&nbsp;&nbsp;
					<a href="bookServlet?method=clear">清空购物车</a>
					&nbsp;&nbsp;
					<a href="bookServlet?method=forwardPage&page=cash">结账</a>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>