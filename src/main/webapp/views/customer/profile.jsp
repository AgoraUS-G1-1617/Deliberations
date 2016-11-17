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

	<h3><spring:message code="customer.info"/></h3>
	<br />
		
		<table style="margin: 0 auto;">
			<tr>
				<td><div class="thumbnail"><img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png"></div></td>
				<td> </td>
				<td nowrap style='padding-left: 5%'>
					<h4><spring:message code="customer.name"/>: <b><jstl:out value=" ${user.name}"/></b></h4>
					<h4><spring:message code="customer.surname"/>: <b><jstl:out value=" ${user.surname}"/></b></h4> 
					<h4><spring:message code="customer.genre"/>: <b><jstl:out value=" ${user.genre}"/></b></h4> 
					<h4><spring:message code="customer.email"/>: <b><jstl:out value=" ${user.email}"/></b></h4> 
				</td>
			</tr>
		</table>
	
	
	<div class="thumbnail">

		<h4><spring:message code="customer.rank"/>: </h4>

	</div>
		
		
		
</div>


<br />