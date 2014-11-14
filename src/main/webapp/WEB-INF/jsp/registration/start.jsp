<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<meta http-equiv="cache-control" content="no-cache" />
		<title>Register</title>
<link href="<c:out value="${pageContext.servletContext.contextPath}" />/css/epc.css" rel="stylesheet"
	type="text/css" />
	</head>

	<body>
	<form:form method="post" commandName="member">
		First Name: <form:input path="firstname" /><FONT color="red"><form:errors path="firstname" /></FONT><br/>
		Family Name: <form:input path="lastname" /><FONT color="red"><form:errors path="lastname" /></FONT><br/>
		Email: <form:input path="email" /><FONT color="red"><form:errors path="email" /></FONT><br/>
		Username: <form:input path="username" /><FONT color="red"><form:errors path="username" /></FONT><br/>
		Password: <form:password path="password" /><FONT color="red"><form:errors path="password" /></FONT><br/>
		<input type="submit" value="Submit" />
	</form:form>
	
	</body>
</html>