<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>



<div class="alert alert-info alert-dismissable" id="mensaje-cookie">

	<div class="message-alert">
		<spring:message code="header.cookie.message" />
		<strong><a href="./policy/cookies.do" class="cookie-moreinfo"><spring:message
					code="header.cookie.message.info" /></a></strong> <strong><a href=""
			class="cookie-close"><spring:message
					code="header.cookie.message.close" /></a></strong>

	</div>
</div>
<script>

<!-- Script para saber si el usuario ha leido el mensaje sobre el uso de cookies -->

	var mensaje = document.cookie.split('mensaje=')[1]; // obtenemos la cookie "mensaje"

	if(mensaje != null){
		 $("#mensaje-cookie").alert('close');
	}		
	
	$(".cookie-close").click(function() {
		document.cookie = 'mensaje=visto;path=/'; // la agregamos
		});
	
</script>

<div class="header_nav" id="home">
	<div class="container">
		
		<nav class="navbar navbar-default chn-gd">
			<div class="logoDlb">
			<a href="" > 
			
			
			<svg height="46" width="46" xmlns:svg="http://www.w3.org/2000/svg" version="1.2" xmlns:xlink="http://www.w3.org/1999/xlink" >
  				<image id="dlb" height="42" width="42"  xlink:href="images/logo.svg" />
			</svg>
			</a>
		</div>
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href=""> <span
							class="glyphicon glyphicon-home " aria-hidden="true"></span> <spring:message
								code="master.page.home" />
					</a></li>
					<!---->

					<security:authorize access="isAuthenticated()">
						<li><a href="thread/list.do"> <span
								class="glyphicon glyphicon-tasks " aria-hidden="true"></span> <spring:message
									code="master.page.forum" />
						</a></li>

						<li><a href="dashboard/list.do"> <span
								class="glyphicon glyphicon-align-center" aria-hidden="true"></span>
								<spring:message code="master.page.dashboard" />
						</a></li>

						<li class="dropdown ">
							<a href="" class="dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
								<spring:message code="master.page.messages" />
								<span class="caret"></span>
							</a>
							
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="message/user/received.do?page=1"><spring:message code="master.page.messages.received" /></a></li>
								<li><a href="message/user/sent.do?page=1"><spring:message code="master.page.messages.sent" /></a></li>
								<li><a href="message/user/create.do"><spring:message code="master.page.messages.create" /></a></li>
							</ul>
						</li>
						
						<li class="dropdown "><a href="" class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-user " aria-hidden="true"></span><spring:message code="master.page.profile" /><span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="user/profile.do">
									<spring:message code="master.page.info" /></a></li>
								<li><a href="j_spring_security_logout">
									<spring:message code="master.page.signout" /></a></li>
							</ul>
						</li>

					</security:authorize>

					<!---->

					<security:authorize access="isAnonymous()">
						<li><a href="user/login.do"><span class="glyphicon glyphicon-user " aria-hidden="true"></span><spring:message
											code="master.page.signin" />

						</a></li>
					</security:authorize>
					<!---->
					<div class="clearfix"></div>
					<!--script-->
					<script type="text/javascript">
						jQuery(document).ready(function($) {
							$(".scroll").click(function(event) {
								event.preventDefault();
								$('html,body').animate({
									scrollTop : $(this.hash).offset().top
								}, 900);
							});
						});
					</script>
					<!--script-->
				</ul>
				<!-- /.navbar-collapse -->
				<div class="clearfix"></div>
				<!-- /.container-fluid -->

			</div>

		</nav>

	</div>
</div>
