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

<br />
<br />
<div class="container">
			<form:form action="${actionURI}" method="post"
				modelAttribute="thread" class="form-horizontal" role="form">

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="user" />
				<form:hidden path="creationMoment" />
				<form:hidden path="comments" />
				<form:hidden path="ratings" />

				<div class="form-group">
					<form:label path="title" class="control-label col-md-2" for="email">
						<spring:message code="thread.title" />
					</form:label>
					<div class="col-md-8">
						<form:input path="title" class="form-control" id="email" />
						<form:errors class="error" path="title" />
					</div>
				</div>

				<div class="form-group">
					<form:label path="decription" class="control-label col-md-2" for="pwd">
						<spring:message code="thread.text" />
					</form:label>
					<div class="col-md-8">
						<form:textarea rows="3" path="decription" class="form-control noresize"
							id="pwd" />
						<form:errors class="error" path="decription" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-10">

						<button type="submit" name="save" class="btn btn-primary" style="float: right"><spring:message code="thread.save" /></button>  
					</div>
				</div>

			</form:form>
		</div>

<br />