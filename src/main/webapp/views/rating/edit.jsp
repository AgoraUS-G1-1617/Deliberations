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
				modelAttribute="rating" class="form-horizontal" role="form">

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="user" />
				<form:hidden path="thread" />
				
				
				

				<div class="jumbotron">
				  <spring:message code="rating.rating" />
					<form:select path="rate" class="form-control">
				   		 <form:options items="${ratings}"/>
					</form:select>
				</div>


			<br />

				<div class="form-group">
					<div class="col-md-10">

						<button type="submit" name="save" class="btn btn-primary" style="float: right"><spring:message code="thread.save" /></button>  
					</div>
				</div>

			</form:form>
		</div>

<br />