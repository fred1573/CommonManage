<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="snippets/root.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="page/js/login.js"></script>
</head>
<body>
	<p align="center">user login entry</p>
	<form:form action="${root }/doLogin" method="post" commandName="userForm" onsubmit="return login()">
		<table align="center" border="1">

			<tr>
				<td>username:</td>
				<td><form:input path="name" id="name" size="22" maxLength="20"/></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><form:password path="password" id="password" size="22" maxLength="10"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="login"/>
				</td>
			</tr>
		</table>
		<h3 align="center">
			<a href="register">join in us</a>
		</h3>
	</form:form>
</body>
</html>