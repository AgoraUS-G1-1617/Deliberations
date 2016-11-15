<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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
<style>
<!--

#cookies {
	font-size: 18px;
	position: relative;
	width: 70%;
	height: 100%;
	display: block;
	margin-top: 5%;
	margin-bottom: 5%;
	margin-left: auto;
	margin-right: auto;
}
.table{
	margin-bottom:3%;
	margin-top:3%;
}

h3{
	margin-bottom:3%;
	margin-top:3%;
}
ul{
	margin-bottom:3%;
	margin-top:3%;
}


-->
</style>
<div id="cookies">

	<p>
		<spring:message code="cookies.text.p1" />
	</p>

	<h3>
		<spring:message code="cookies.text.h3_1" />
	</h3>

	<p>
		<spring:message code="cookies.text.p2" />

		<a href="http://es.wikipedia.org/wiki/Cookie_(inform%C3%A1tica)">link</a>.
	</p>

	<h3>
		<spring:message code="cookies.text.h3_2" />
	</h3>

	<p>
		<spring:message code="cookies.text.p3" />
	</p>

	<p>
		<spring:message code="cookies.text.p4" />
	</p>

	<p>
		<spring:message code="cookies.text.p5" />
	</p>

	<p>
		<spring:message code="cookies.text.p6" />
	</p>

	<h3>
		<spring:message code="cookies.text.h3_3" />
	</h3>
	<p>
		<spring:message code="cookies.text.p7" />
	</p>
	<table class="table">
		<thead>
			<tr>
				<th><spring:message code="cookies.table.head1" /></th>
				<th><spring:message code="cookies.table.head2" /></th>
				<th><spring:message code="cookies.table.head3" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><spring:message code="cookies.table.row1_1" /></td>
				<td><spring:message code="cookies.table.row1_2" /></td>
				<td><spring:message code="cookies.table.row1_3" /></td>
			</tr>
			<tr>
				<td><spring:message code="cookies.table.row2_1" /></td>
				<td><spring:message code="cookies.table.row2_2" /></td>
				<td><spring:message code="cookies.table.row2_3" /></td>
			</tr>

		</tbody>
	</table>


	<h3>
		<spring:message code="cookies.text.h3_4" />
	</h3>
	<p>
		<spring:message code="cookies.text.p8" />
	</p>

	<p>
		<spring:message code="cookies.text.p9" />
	</p>

	<ul class="list-group">
		<li class="list-group-item"><spring:message code="cookies.list1_1" /></li>
		<li class="list-group-item"><spring:message code="cookies.list1_2" /></li>
		<li class="list-group-item"><spring:message code="cookies.list1_3" /></li>
		<li class="list-group-item"><spring:message code="cookies.list1_4" /></li>
	</ul>

	
	<p>
		<spring:message code="cookies.text.p10" />
	</p>

	<p>
		<spring:message code="cookies.text.p11" />
	</p>

	<ul class="list-group">
		<li class="list-group-item"><spring:message code="cookies.list2_1" /></li>
		<li class="list-group-item"><spring:message code="cookies.list2_2" /></li>
		<li class="list-group-item"><spring:message code="cookies.list2_3" /></li>
		<li class="list-group-item"><spring:message code="cookies.list2_4" /></li>
		<li class="list-group-item"><spring:message code="cookies.list2_5" /></li>
		
	</ul>
	<p>
		<spring:message code="cookies.text.p12" />
	</p>
</div>

