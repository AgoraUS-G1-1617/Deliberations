<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

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
	<display:table name="threads" id="row" requestURI="thread/list.do"
		pagesize="15" class="table table-striped">


		<spring:message var="titleHeader" code="thread.title" />
		<display:column title="${titleHeader}">
			<a href="thread/display.do?id=${row.id}&p=1"><jstl:out
					value="${row.title }"></jstl:out></a>
		</display:column>


		<spring:message var="authorHeader" code="thread.author" />
		<display:column title="${authorHeader}">
			<jstl:out value="${row.user.name }"></jstl:out>
		</display:column>


		<spring:message var="dateHeader" code="thread.date" />
		<display:column title="${dateHeader}" property="creationMoment"
			format="{0,date,dd/MM/yyyy HH:mm}">
			<jstl:out value="${row.creationMoment}"></jstl:out>
		</display:column>

		<spring:message var="answersHeader" code="thread.answers" />
		<display:column title="${answersHeader}">
			<span class="badge"> <jstl:out value="${row.comments.size()}"></jstl:out>
			</span>
		</display:column>
		
		
			<display:column>
				<a href="rating/edit.do?threadId=${row.id}">
				<spring:message code="thread.rate" />
			</a>
			</display:column>	

	</display:table>
</div>
</div>
<br />