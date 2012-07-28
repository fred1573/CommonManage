<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../snippets/root.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" border="1">
		<tr>
			<th>username</th>
			<th>email</th>
			<th>register-time</th>
			<th>isAdmin</th>
			<th>available</th>
			<th></th>
		</tr>
		<c:forEach items="${list }" var="user">
			<tr>
				<td>${user.name }</td>
				<td>${user.email }</td>
				<td>${user.registerTime }</td>
				<td>${user.admin }</td>
				<td>${user.available }</td>
				<td>
					<c:if test="${user.available}">
						<c:if test="${user.admin == false}">
							<a href="lock?id=${user.id }">lock</a>
						</c:if>
					</c:if> 
					<c:if test="${user.available == false }">
						<a href="unlock?id=${user.id }">unlock</a>
					</c:if>
				</td>
				<td>
					<a href="delete?id=${user.id }">delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="javascript:history.go(-1);">向上一页</a>
</body>
</html>