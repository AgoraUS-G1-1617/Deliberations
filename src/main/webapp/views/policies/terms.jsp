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
#terms {
	font-size: 18px;
	position: relative;
	width: 70%;
	height: 100%;
	display: block;
	margin: 0 auto;
}
.terms-data {
	margin-top: 5%;

	
}

.terms-lopd {
	margin-top: 5%;
	margin-botton: 2%;
	margin-left: 7%;
	
}
.terms-list{

	margin-top:2%;
	margin-botton:2%;
}
-->
</style>


<div id="terms">

	<ol class="terms-data" type="1">
		<li class="terms-list"><spring:message code="terms.text.p1" /></li>
		<li class="terms-list"><spring:message code="terms.text.p2" /></li>
		<li class="terms-list"><spring:message code="terms.text.p3" /></li>
		<li class="terms-list"><spring:message code="terms.text.p4" /></li>
	</ol>

	<ol class="terms-lopd" type="a">
		<li class="terms-list"><spring:message code="terms.text.p5" /></li>
		<li class="terms-list"><spring:message code="terms.text.p6" /></li>
		<li class="terms-list"><spring:message code="terms.text.p7" /></li>
		<li class="terms-list"><spring:message code="terms.text.p8" /></li>

	</ol>


</div>

