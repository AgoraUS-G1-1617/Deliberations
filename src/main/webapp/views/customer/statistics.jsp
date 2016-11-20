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

	<h3><spring:message code="user.statistics"/></h3>
	<br />
	<br />
	<h4>
		<table style="margin: 0 auto;">
			<tr>
				<td><spring:message code="user.messagesSent" />: </td>
				<td><b><jstl:out value=" ${messagesSent}" /></b></td>
			</tr>

			<tr>
				<td><spring:message code="user.messagesReceived" />: </td>
				<td><b><jstl:out value=" ${messagesReceived}" /></b></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.threadsCreated" />: </td>
				<td><b><jstl:out value=" ${threadsCreated}" /></b></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.commentsCreated" />: </td>
				<td><b><jstl:out value=" ${commentsCreated}" /></b></td>
			</tr>
		</table>		
		</h4>
</div>


<br />
<br />
