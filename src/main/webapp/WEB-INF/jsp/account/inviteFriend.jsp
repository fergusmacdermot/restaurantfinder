<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="cache-control" content="no-cache" />
		<title>Send invite</title>
		<link rel="stylesheet" type="text/css" href="/css/default.css" />

	</head>

	<body>
	<!--link to my home-->
	<div id="myHomeLink"><a href="/web/account/<c:out value="${member.memberId}"/>"/>home</a></div>
    <div id="userSearchBar">
    	Leave blank for defaults
	<form:form method="post" action="/web/member/invite" commandName="emailMessage">
		Email:<form:input path="toEmail" /><FONT color="red"><form:errors path="toEmail" /></FONT><br/>
		Subject:<form:input path="subject" /><FONT color="red"><form:errors path="subject" /></FONT><br/>
		Message:<form:textarea path="message" /><FONT color="red"><form:errors path="message" /></FONT><br/>
	<c:choose>
		<c:when test="${error != null}">
		<div class="warning">
		  <c:out value="${error}"/>
		</div>
		</c:when>
		<c:otherwise>				
		<%--do nothing --%>
		</c:otherwise>
	</c:choose>	
	<c:choose>
		<c:when test="${foundMember.memberId != null}">
		<div id="resultsHeader">
		  That member exists, please send an internal invite:
		</div>
		<div>
		Username: <c:out value="${foundMember.username}"/> (<a href="/web/member/request/<c:out value="${member.memberId}"/>/<c:out value="${foundMember.memberId}"/>"><c:out value="${foundMember.firstname}" /> <c:out value="${foundMember.lastname}" /></a>)
		</div>
		</c:when>
		<c:otherwise>				
		<%--do nothing --%>
		</c:otherwise>
	</c:choose>	
			
		<input type="submit" value="Send" />
	</form:form>	
	</body>
</html>