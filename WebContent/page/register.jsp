<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="snippets/root.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function passwordCheck() {
		var pass = document.getElementById("password").value;
		if (pass == null || pass.trim().length == 0 || pass.trim().length > 10) {
			alert("the size of the password should be at least 1 and not be longer than 10");
			document.getElementById("password").value="";
			document.getElementById("password").focus();
			return false;
		}
	}
	
	var flag = false;
	function repeatCheck(){
		if(flag == true){
			return false;
		}
		flag = true;
		return true;
	}
	document.onclick = function doconclick(){
		if(flag == true){
			window.event.returnValue = false;
		}
	}
</script>
<script type="text/javascript" src="page/js/registerCheck.js"></script>
</head>
<body>
	<form:form action="doRegister" method="post" commandName="userForm" onsubmit="return passwordCheck();">
		<table align="center" border="1">
			<tr>
				<td>username:</td>
				<td><form:input path="name" id="name" /> <input
					type="button" onclick="userNameCheck()" value="check" /></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><form:input path="password" id="password"/></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><form:input path="email" id="email" /> <input
					type="button" onclick="emailCheck()" value="check" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="finish" id="disable"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>