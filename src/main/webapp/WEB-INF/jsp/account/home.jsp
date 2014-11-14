<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<meta http-equiv="cache-control" content="no-cache" />
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.servletContext.contextPath}" />/css/default.css" />

	</head>

	<body>
	<div class="standardText">
	Logged in as: <sec:authentication property="principal.username" />
	</div>	
	<div class="standardText"> 
	<c:out value="${member.username}"/>&#146;s home
	</div>
	<div class="standardLink">
		<a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/member/list/<c:out value="${member.memberId}"/>">friend list</a>
	</div>
	
	<%-- this part below only if authenticated user --%>
	<c:if test="${member.memberId == authenticatedMember.memberId}"> 
	<form:form method="post"  action="../member/search" commandName="holder">
	Find friends: <form:input path="keyword" /><input type="submit" value="Find" /> (user name or part of name)
	</form:form>
	<%-- errors from search keyword--%> 
		<c:if test="${keywordError != null}">
		<div class="warning">
		  <c:out value="${keywordError}"/>
		</div>
		</c:if>
	</c:choose>	
	<div id="friendsRequestBox">
		<div id="requestBoxTitle">Requests to me - click to accept</div>							
			<div>
			<%--get results from map --%>
			<c:forEach var="entry" items="${holder.results}"> 
				<c:choose>
					<c:when test="${entry.key == 'requested'}">
						<%--iterate through results --%>
						<c:forEach var="result" items="${entry.value}"> 
						<div>
						<a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/member/request/<c:out value="${member.memberId}"/>/<c:out value="${result.memberId}"/>"><c:out value="${result.username}" /></a> (<c:out value="${result.firstname}" /> <c:out value="${result.lastname}" />)
						</div>
						</c:forEach>
					<%-- end map iterator --%>
					</c:when>
					<c:otherwise>
						<div id="noResultsText">No requests</div>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			</div>
			
			
	</div>
	<%-- these are requests by me to others who have not response--%>
	<div id="friendsResponseBox">
	<%-- errors from sending too many requests--%> 
	<c:choose>
		<c:when test="${errorMessage != null}">
		<div class="warning">
		  <c:out value="${errorMessage}"/>
		</div>
		</c:when>
		<c:otherwise>				
		<%--do nothing --%>
		</c:otherwise>
	</c:choose>		
		<div id="responseBoxTitle">Requests awaiting responses (click for reminder)</div>
			<%--get results from map --%>
			<c:forEach var="entry" items="${holder.results}"> 
				<c:choose>
					<c:when test="${entry.key == 'awaiting response'}">
						<%--iterate through results --%>
						<c:forEach var="result" items="${entry.value}"> 
						<div>
						<a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/member/request/<c:out value="${member.memberId}"/>/<c:out value="${result.memberId}"/>"><c:out value="${result.username}" /></a> (<c:out value="${result.firstname}" /> <c:out value="${result.lastname}" />)
						</div>
						</c:forEach>
					<%-- end map iterator --%>
					</c:when>
					<c:otherwise>
						<div id="noResultsText">No responses pending</div>
					</c:otherwise>
				</c:choose>	
			</c:forEach>	
	</div>
	<%--end if for authenticated user section --%>
	</c:if>
	<div class="standardLink">
		<a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/logout">logout</a>
	</div>
	</body>
</html>