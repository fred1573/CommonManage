<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.frd.model.User"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<a href="produce">produce an account in record</a>
	<table align="center">
		<tr>
			<th colspan="5"><h3>account in list</h3></th>
		</tr>
		<tr>
			<th>user-id</th>
			<th>create-time</th>
			<th>number</th>
			<th>status</th>
			<th>checked-by-who</th>
			<th></th>
		</tr>
		<c:forEach items="${list }" var="item">
			<tr>

				<td>${item.uid }#${user.name }</td>
				<td>${item.time }</td>
				<td>${item.number }</td>
				<td>
					<c:if test="${item.verify}">
						checked
					</c:if> <c:if test="${!item.verify}">
						unchecked
					</c:if>
				</td>
				<td>
					<!-- 已通过验证的用户id拼接 --> 
					<c:if test="${checkedUserIdMap != null }">
						<c:forEach items="${checkedUserIdMap }" var="checkedUserTemp">
							<c:if test="${checkedUserTemp.key == item.id }">
								${checkedUserTemp.value }
							</c:if>
						</c:forEach>
					</c:if>
				</td>
				<td>
					<c:if test="${!item.verify }">
						<c:forEach items="${allowRepeatCheck }" var="allowRepeatTemp">
							<c:if test="${item.id == allowRepeatTemp.key && allowRepeatTemp.value}">
								<a href="check?id=${item.id }">check it</a>
							</c:if>
						</c:forEach>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>