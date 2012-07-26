<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(request.getSession().getAttribute("root") == null){
		request.getSession().setAttribute("root", request.getContextPath());
	}
%>