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

<br />
<br />

<div class="container marketing">

	<div class="row">
		<div class="col-lg-4" style="text-align:center;">
			<img class="img-square"
				src="https://image.flaticon.com/icons/svg/265/265755.svg"
				alt="person" width="140" height="140">
			<h2><spring:message code="welcome.heading.1"/></h2>
			<p><spring:message code="welcome.1.text"/></p>
		</div>
		<!-- /.col-lg-4 -->
		<div class="col-lg-4" style="text-align:center;">
			<img class="img-square"
				src="https://image.flaticon.com/icons/svg/139/139756.svg"
				alt="key" width="140" height="140">
			<h2><spring:message code="welcome.heading.2"/></h2>
			<p><spring:message code="welcome.2.text"/></p>
		</div>
		<!-- /.col-lg-4 -->
		<div class="col-lg-4" style="text-align:center;">
			<img class="img-square"
				src="https://image.flaticon.com/icons/svg/138/138533.svg"
				alt="heart" width="140" height="140">
			<h2><spring:message code="welcome.heading.3"/></h2>
			<p><spring:message code="welcome.3.text"/></p>
		</div>
		<!-- /.col-lg-4 -->
	</div>
	<!-- /.row -->

	<hr class="featurette-divider">

	<div class="row featurette">
		<div class="col-md-7">
			<h2 class="featurette-heading">
				<spring:message code="welcome.feature.1.title"/> 
				<span class="text-muted"><spring:message code="welcome.feature.1.sub"/></span>
			</h2>
			<p class="lead"><spring:message code="welcome.intro"/></p>
		</div>
		<div class="col-md-5">
			<img class="featurette-image img-responsive center-block"
				src="https://image.flaticon.com/icons/svg/281/281351.svg" alt="voting" width="50%">
		</div>
	</div>

	<hr class="featurette-divider">

	<div class="row featurette">
		<div class="col-md-7 col-md-push-5">
			<h2 class="featurette-heading">
				<spring:message code="welcome.feature.2.title"/> 
				<span class="text-muted"><spring:message code="welcome.feature.2.sub"/></span>
			</h2>
			<p class="lead"><spring:message code="welcome.second"/></p>
		</div>
		<div class="col-md-5 col-md-pull-7">
			<img class="featurette-image img-responsive center-block"
				src="https://image.flaticon.com/icons/svg/291/291684.svg" alt="programming" width="50%">
		</div>
	</div>

	<hr class="featurette-divider">
</div>