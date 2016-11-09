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

<!-- <div> -->
<!-- 	<img src="images/logo.png" alt="Sample Co., Inc." /> -->
<!-- </div> -->

<!-- <div> -->
<!-- 	<ul id="jMenu"> -->
<!-- 		<!-- Do not forget the "fNiv" class for the first level links !! -->
<%-- 		<security:authorize access="hasRole('ADMIN')"> --%>
<%-- 			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a> --%>
<!-- 				<ul> -->
<!-- 					<li class="arrow"></li> -->


<!-- 				</ul> -->
<!-- 			</li> -->
<%-- 		</security:authorize> --%>

<%-- 		<security:authorize access="hasRole('CUSTOMER')"> --%>
<%-- 			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a> --%>
<!-- 				<ul> -->
<!-- 					<li class="arrow"></li> -->

<%-- 					<li><a href="customer/listThreads.do"><spring:message code="master.page.customer.listThreads" /></a></li>		 --%>
<%-- 					<li><a href="customer/createThread.do"><spring:message code="master.page.customer.createThread" /></a></li>		 --%>
<!-- NECESARIO -->
<%-- 		<security:authorize access="hasRole('USER')"> --%>
<%-- 			<li><a class="fNiv"><spring:message	code="master.page.user" /></a> --%>
<!-- 				<ul> -->
<!-- 					<li class="arrow"></li> -->

<%-- 					<li><a href="thread/list.do"><spring:message code="master.page.customer.listThreads" /></a></li>		 --%>
<%-- 					<li><a href="thread/create.do"><spring:message code="master.page.customer.createThread" /></a></li>		 --%>

<!-- 				</ul> -->
<!-- 			</li> -->
<%-- 		</security:authorize> --%>

<%-- 		<security:authorize access="isAnonymous()"> --%>
<%-- 			<li><a class="fNiv" href="customer/login.do"><spring:message code="master.page.login.A" /></a></li> --%>
<%-- 			<li><a class="fNiv" href="customer/loginFromCensusForm.do"><spring:message code="master.page.login.C" /></a></li> --%>
<!-- NECESARIO -->
<%-- 		<security:authorize access="isAnonymous()"> --%>
<%-- 			<li><a class="fNiv" href="user/login.do"><spring:message code="master.page.login.A" /></a></li> --%>
<%-- 			<li><a class="fNiv" href="user/loginFromCensusForm.do"><spring:message code="master.page.login.C" /></a></li> --%>





<%-- 		</security:authorize> --%>

<%-- 		<security:authorize access="isAuthenticated()"> --%>
<!-- 				<li> -->
<!-- 				<a class="fNiv">  -->
<%-- 					<spring:message code="master.page.profile" />  --%>
<%-- 			        (<security:authentication property="principal.username" />) --%>
<!-- 				</a> -->
<!-- 				<ul> -->
<!-- 					<li class="arrow"></li> -->

<%-- 					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li> --%>
<!-- 				</ul> -->
<!-- 			</li> -->
<%-- 		</security:authorize> --%>
<!-- 	</ul> -->
<!-- </div> -->

<!-- <div> -->
<!-- 	<a href="?language=en">en</a> | <a href="?language=es">es</a> -->
<!-- </div> -->

<div class="header">
	<div class="container">
		<div class="logo">
			<a href="/welcome.do"><img src="images/logo.png"
				class="img-responsive" alt="Deliberations - Agora@US" /></a>
		</div>
		<div class="head-mid"></div>
		<div class="ad-ph">
			<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			<p style="float: right">
				<security:authorize access="isAuthenticated()">
					<h6>
						<spring:message code="master.page.welcome" />
					</h6>
					<span style="float: left; padding-right: 2px"> <security:authentication
							property="principal.username" />
					</span>
				</security:authorize>
			</p>
			<span><a href="?language=es"><img src="images/es.gif" />
			</a> | <a href="?language=en"><img src="images/en.gif" /></a></span> <br>

		</div>
		<div class="clearfix"></div>
	</div>
</div>
<div class="header_nav" id="home">
	<div class="container">
		<nav class="navbar navbar-default chn-gd">

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
						
						<li><a href="dashboard/list.do"> <span class="glyphicon glyphicon-align-center" aria-hidden="true"></span> <spring:message
									code="master.page.dashboard" />
						</a></li>

						<li class="dropdown"><a href="" class="dropdown-toggle"
							data-toggle="dropdown"><spring:message
											code="master.page.options" /><span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="j_spring_security_logout"><spring:message
											code="master.page.signout" /></a></li>
							</ul></li>

					</security:authorize>

					<!---->

					<security:authorize access="isAnonymous()">
						<li class="dropdown"><a href="" class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-cog "
								aria-hidden="true"></span> <spring:message
											code="master.page.options" /> <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="user/login.do"><spring:message
											code="master.page.signin" /></a></li>
							</ul></li>
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
