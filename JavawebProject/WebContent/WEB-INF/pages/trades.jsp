<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
		<center>
			<br><br>
			<h4>User:${user.user_name}</h4>
			<br><br>
			<table>
				<c:forEach items="${user.trades }" var="trade">
					<tr>
					<td>
					<table border="1" cellpadding="10" cellspacing="0">
						<tr>
							<td colspan="3">TradeTime:${trade.trade_time}</td>
						</tr>
						<c:forEach items="${trade.items }" var="item">
							<tr>
								<td>${item.book.title}</td>
								<td>${item.book.price}</td>
								<td>${item.quantity}</td>
							</tr>
						</c:forEach>
					</table>
					<br><br>
					</td>
					</tr>
				</c:forEach>
			</table>
		</center>
</body>
</html>