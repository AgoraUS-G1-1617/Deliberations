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
<div class="container">

	<div class="row featurette">
		<div class="col-md-7">
			<h1 class="featurette-heading">
				<spring:message code="welcome.title" /><span class="text-muted"></span>
			</h1>
			<p class="lead"><spring:message code="welcome.intro" /></p>
		</div>
		<div class="col-md-5">
			<img src="images/group.png"
				class="featurette-image img-responsive center-block"
				data-src="holder.js/500x500/auto" alt="Generic placeholder image">
		</div>
	</div>

	<hr class="featurette-divider">

	<div class="row featurette">
		<div class="col-md-7 col-md-push-5">
			<h2 class="featurette-heading">
				Agora@US<span class="text-muted"></span>
			</h2>
			<p class="lead"><spring:message code="welcome.second" /></p>
		</div>
		<div class="col-md-5 col-md-pull-7">
			<img src="images/group.png"
				class="featurette-image img-responsive center-block"
				data-src="holder.js/500x500/auto" alt="Grupo EGC">
		</div>
	</div>
	<br />
</div>

<!-- welcome -->
<!-- <div class="welcome"> -->
<!-- <div class="container"> -->
<!-- 		<h2>Deliberaciones Agora@US 15-16</h2> -->
<!-- 		<p> -->
<!-- 			Deliberaciones es un subsistema de c�digo abierto del proyecto -->
<!-- 			Agora@US que introduce la funcionalidad de un foro para administrar -->
<!-- 			hilos y mensajes de usuarios votantes. <br /> Dichos mensajes -->
<!-- 			contendr�n informaci�n sobre el votante que los haya realizado. S�lo -->
<!-- 			los votantes v�lidos podr�n realizar comentarios, por lo que se -->
<!-- 			deber� consultar al sistema de autenticaci�n. <br /> -->
<!-- 			<br /> -->
<!-- 		</p> -->

<!-- </div> -->
<!-- </div> -->
<!-- <!-- welcome -->
