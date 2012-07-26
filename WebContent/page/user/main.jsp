<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<p>welcome, ${user.name }</p>

	<a href="balance/sum">balance</a><br/>
	<a href="accountIn/list">accountInList</a><br/>
	<a href="accountOut/list">accountOutList</a>
	<br />
	<c:if test="${user.admin}">
		<a href="user/list">userlist</a>
	</c:if>
	<br />
	<a href="user/personalData">personal data</a>
	<br />
	<a href="user/exit">exit safely</a>	

</body>
</html>