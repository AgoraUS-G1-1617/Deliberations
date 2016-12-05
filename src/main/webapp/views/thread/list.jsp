<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.simplePagination.js"></script>


<br />
<br />
<div class="container">

	<h3><spring:message code="thread.head" /></h3>

	<div id="create-button" style="text-align: right">
		<a href="thread/create.do" class="btn btn-primary" role="button"><span
			class="glyphicon glyphicon-pencil" aria-hidden="true"></span><spring:message code="list.new" /></a>
	</div>
<div class="table-responsive">
	<display:table name="threads" id="row" requestURI="thread/list.do" pagesize="15" class="table table-striped">

		<spring:message var="titleHeader" code="thread.title" />
		<display:column title="${titleHeader}">
			<a href="thread/display.do?id=${row.id}&p=1"><jstl:out
					value="${row.title }"></jstl:out></a>
			<!-- Chunk for closed threads -->
			<jstl:if test="${row.closed}">
  				<span id="lock-icon" class="glyphicon glyphicon-lock" aria-hidden="true" ></span>
			</jstl:if>
			<!-- ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^  -->
			<security:authorize  access="isAuthenticated()">
				<jstl:if test="${row.user.id == actUserId}">
					&nbsp (<a href="thread/edit.do?threadId=${row.id}">edit</a>)
				</jstl:if>
			</security:authorize>
		</display:column>

		<spring:message var="authorHeader" code="thread.author" />
		<display:column title="${authorHeader}">
			<jstl:out value="${row.user.name }"></jstl:out>
		</display:column>

		<spring:message var="dateHeader" code="thread.creationMoment" />
		<display:column title="${dateHeader}" property="creationMoment"
			format="{0,date,dd/MM/yyyy HH:mm}">
			<jstl:out value="${row.creationMoment}"></jstl:out>
		</display:column>
		
		<spring:message var="lastCommentHeader" code="thread.lastComment" />
		<display:column title="${lastCommentHeader}">
			<fmt:formatDate value="${lastComments.get(row.id).creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
		</display:column>

		<spring:message var="answersHeader" code="thread.answers" />
		<display:column title="${answersHeader}">
			<span class="badge"> <jstl:out value="${row.comments.size()}"></jstl:out>
			</span>
		</display:column>
		
		<display:column>
			<!-- Chunk for thread rating -->
			<jstl:forEach var="i" begin="1" end="${row.rating}">
				<img src='images/star.png' alt='*' height='20px' />
			</jstl:forEach>
			<jstl:forEach var="i" begin="${row.rating}" end="4">
				<img src='images/star_n.png' alt='*' height='20px' />
			</jstl:forEach>
			<!-- ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ -->
		</display:column>	
	</display:table>
</div>
</div>
<br />