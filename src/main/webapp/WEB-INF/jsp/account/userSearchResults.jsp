<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="cache-control" content="no-cache" />
		<title>Search</title>
	<link rel="stylesheet" type="text/css" href="/css/default.css" />
	</head>

	<body>
	<!--link to my home-->
	<div id="myHomeLink"><a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/account/<c:out value="${authenticatedMember.memberId}"/>"/>home</a></div>
    <!-- the search bar for users -->
    <div id="userSearchBar">
	<form:form method="post" action="search" commandName="holder">
	Search again: <form:input path="keyword" /><input type="submit" value="Find" />
	</form:form>
	</div>
	<c:choose>
		<c:when test="${holder.totalResults == 0}">
		<div id="noResultsText">No results returned</div>
		</c:when>
		<c:otherwise>		
	<%--get results from map --%>
	<c:forEach var="entry" items="${holder.results}"> 
	<%--check the entry type are the keyword results --%>
	<c:if test="${entry.key == 'keyword'}">
	<%-- if so iterate through the results --%>
	<c:forEach var="result" items="${entry.value}"> 		
		<div><fmt:formatNumber value="${holder.currentPage * holder.resultsPerPage +1}"/> - <fmt:formatNumber value="${(holder.currentPage * holder.resultsPerPage)+fn:length(entry.value)}"/> of <fmt:formatNumber value="${holder.totalResults  }"/>
		</div>			
	<div id="resultsHeader">
	  Username (name - click to request link)
	</div>

	<div>
	
	<%-- don't show the link when it's same user --%>
	<c:choose>
		<c:when test="${result.memberId == authenticatedMember.memberId}">
		<c:out value="${result.username}" />
		</c:when>
		<c:otherwise>			
		<%-- when a friend show home page link - used when browsing around --%>
		<c:choose>
			<c:when test="${fn:contains( authenticatedMember.acceptedFriends, result.memberId )}">
			<a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/account/<c:out value="${result.memberId}"/>"><c:out value="${result.username}" /></a>
			</c:when>
			<c:otherwise>
			<%-- when a friend show home page link - used when browsing around --%>
			<c:out value="${result.username}" /> <a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/member/request/<c:out value="${authenticatedMember.memberId}"/>/<c:out value="${result.memberId}"/>">+</a>
			
			</c:otherwise>
		</c:choose>				
	
		</c:otherwise>
	</c:choose>	
	(<c:out value="${result.firstname}" /> <c:out value="${result.lastname}" />)	
	
	</div>
	</c:forEach>
	</c:if>
	</c:forEach>
	<div id="pagingBar">
	  <c:if test="${holder.totalPages > 1}">
	   <c:forEach var="i" begin="1" end="${holder.totalPages }">
		  <c:choose>					  
		  <c:when test="${holder.currentPage+1 != i}">
		  <a href="<c:out value="${pageContext.servletContext.contextPath}" />/web/member/search?keyword=${holder.keyword}&amp;currentPage=<c:out value="${i-1}" />"><c:out value="${i}" /></a>
		  </c:when>
		  <c:otherwise>
		    <c:out value="${i}" />
		  </c:otherwise>
		  </c:choose>					   
			            			
	  </c:forEach>
	  </c:if>	
	</div>
	</c:otherwise>
	</c:choose>	
    <%-- paging bar ended above --%>
	</body>
</html>