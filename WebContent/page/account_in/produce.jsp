<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="doProduce" method="post" commandName="accountInForm">
		<table align="center" border="1">
			<tr>
				<td>number</td>
				<td><form:input path="number"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="confirm"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>