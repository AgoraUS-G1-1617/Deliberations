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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<br/>
<br/>

<div class="container">
	<h1>
		<spring:message code="dashboard.threadMostComments" />
	</h1>
	<div class="table-responsive">
		<display:table name="threadMostComments" id="row" requestURI="${requestURI}" class="table table-striped">

			<spring:message code="dashboard.title" var="rowtitle" />
			<display:column title="${rowtitle}">
				<jstl:out value="${row.title}" />
			</display:column>

			<spring:message code="dashboard.creationMoment" var="rowcreationMoment" />
			<display:column title="${rowcreationMoment}">
				<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
			</display:column>

			<spring:message code="dashboard.decription" var="rowdecription" />
			<display:column title="${rowdecription}">
				<jstl:out value="${row.decription}"/>
			</display:column>

		</display:table>
	</div>

	<br />


	<h1>
		<spring:message code="dashboard.threadLeastComments" />
	</h1>

	<div class="table-responsive">
		<display:table name="threadLeastComments" id="row" requestURI="${requestURI}" class="table table-striped">

			<spring:message code="dashboard.title" var="rowtitle" />
			<display:column title="${rowtitle}">
				<jstl:out value="${row.title}" />
			</display:column>

			<spring:message code="dashboard.creationMoment" var="rowcreationMoment" />
			<display:column title="${rowcreationMoment}">
				<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
			</display:column>

			<spring:message code="dashboard.decription" var="rowdecription" />
			<display:column title="${rowdecription}">
				<jstl:out value="${row.decription}"/>
			</display:column>

		</display:table>
	</div>
	<br />

	<h1>
		<spring:message code="dashboard.threadMoreRating" />
	</h1>

	<div class="table-responsive">
		<display:table name="threadMoreRating" id="row" requestURI="${requestURI}" class="table table-striped">

			<spring:message code="dashboard.title" var="rowtitle" />
			<display:column title="${rowtitle}">
				<jstl:out value="${row.title}" />
			</display:column>

			<spring:message code="dashboard.creationMoment" var="rowcreationMoment" />
			<display:column title="${rowcreationMoment}">
				<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
			</display:column>

			<spring:message code="dashboard.decription" var="rowdecription" />
			<display:column title="${rowdecription}">
				<jstl:out value="${row.decription}"/>
			</display:column>

		</display:table>
	</div>
</div>
<br />
<br />