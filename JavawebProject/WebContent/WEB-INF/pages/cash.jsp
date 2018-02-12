<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
	<center>
		<br><br>
		您一共买了${sessionScope.ShoppingCart.bookNumber}本书
		<br>
		应付:￥${sessionScope.ShoppingCart.totalMoney}
		<br><br>
		<c:if test="${requestScope.errors != null }">
			<font color="red">${requestScope.errors}</font>
		</c:if>
		<form action="bookServlet?method=cash" method="post">
			<table cellpadding="10">
				<tr>
					<td>信用卡姓名</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>信用卡账号</td>
					<td><input type="text" name="accountId"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="结账"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>