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
<!-- 			Deliberaciones es un subsistema de código abierto del proyecto -->
<!-- 			Agora@US que introduce la funcionalidad de un foro para administrar -->
<!-- 			hilos y mensajes de usuarios votantes. <br /> Dichos mensajes -->
<!-- 			contendrán información sobre el votante que los haya realizado. Sólo -->
<!-- 			los votantes válidos podrán realizar comentarios, por lo que se -->
<!-- 			deberá consultar al sistema de autenticación. <br /> -->
<!-- 			<br /> -->
<!-- 		</p> -->

<!-- </div> -->
<!-- </div> -->
<!-- <!-- welcome -->

<!-- why -->
<div class="why">
	<h2>
		<p class="lead">
		<center><spring:message code="welcome.team" /></center>
		</p>
	</h2>
	<ul class="ch-grid">
		<li>
			<div class="ch-item ch-img-1">
				<div class="ch-info">
					<h3><spring:message code="welcome.int" /></h3>
					<p>
						Juan García Orozco <a href="#">juagaroro@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
		<li>
			<div class="ch-item ch-img-2">
				<div class="ch-info">
					<h3><spring:message code="welcome.coo" /></h3>
					<p>
						Juan García-Quismondo Fernández <a href="#">juagarfer4@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
		<li>
			<div class="ch-item ch-img-3">
				<div class="ch-info">
					<h3><spring:message code="welcome.ana" /></h3>
					<p>
						Roberto Jiménez Castillo <a href="#">robjimcas@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
		<br />
		<li>
			<div class="ch-item ch-img-4">
				<div class="ch-info">
					<h3><spring:message code="welcome.dis" /></h3>
					<p>
						Francisco José Macías García <a href="#">framacgar2@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
		<li>
			<div class="ch-item ch-img-5">
				<div class="ch-info">
					<h3><spring:message code="welcome.dev" /></h3>
					<p>
						Alfredo Menéndez Charlo <a href="#">alfmencha@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
		<li>
			<div class="ch-item ch-img-6">
				<div class="ch-info">
					<h3><spring:message code="welcome.doc" /></h3>
					<p>
						Rubén Ramírez Vera <a href="#">rubramver@alum.us.es</a>
					</p>
				</div>
			</div>
		</li>
	</ul>
</div>
<!-- why -->
