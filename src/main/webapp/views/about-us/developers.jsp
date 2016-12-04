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

<div class="why">
	<h1>
		<p class="lead">
		<center><spring:message code="developers.title" /></center>
		</p>
	</h1>
	
	<br />
	
	<div class="mainbox col-md-6 col-md-offset-6 col-sm-6 col-sm-offset-6">	
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h2><spring:message code="developers.2016header" /></h2>
				</div>
			</div>
			<div class="panel-body">
	
				<ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-16-1">
							<div class="ch-info">
								<h3><spring:message code="developers.coo" /></h3>
								<p>
									Javier García Calvo <a href="https://github.com/jjxp">@jjxp</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-16-2">
							<div class="ch-info">
								<h3><spring:message code="developers.int" /></h3>
								<p>
									Manuel Francisco López Ruiz <a href="https://github.com/ManuelLR">@ManuelLR</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-16-3">
							<div class="ch-info">
								<h3><spring:message code="developers.dev" /></h3>
								<p>
									  Bartolome Marquez Dominguez <a href="https://github.com/barmardom">@barmardom</a>
								</p>
							</div>
						</div>
					</li>
					<br />
					<li>
						<div class="ch-item ch-img-16-4">
							<div class="ch-info">
								<h3><spring:message code="developers.dev" /></h3>
								<p>
									Juan Ramón Rodríguez Rosado <a href="https://github.com/juanrarodriguez18">@juanrarodriguez18</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-16-5">
							<div class="ch-info">
								<h3><spring:message code="developers.dev" /></h3>
								<p>
									 Jose Antonio Rodríguez Torres <a href="https://github.com/josearodriguez">@josearodriguez</a>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<br />
	<br />
	
	<div class="mainbox col-md-6 col-md-offset-6 col-sm-6 col-sm-offset-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h2><spring:message code="developers.2015header" /></h2>
				</div>
			</div>
			<div class="panel-body">
				<ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-15-1">
							<div class="ch-info">
								<h3><spring:message code="developers.int" /></h3>
								<p>
									Juan García Orozco <a href="#">juagaroro@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-15-2">
							<div class="ch-info">
								<h3><spring:message code="developers.coo" /></h3>
								<p>
									Juan García-Quismondo Fernández <a href="#">juagarfer4@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-15-3">
							<div class="ch-info">
								<h3><spring:message code="developers.ana" /></h3>
								<p>
									Roberto Jiménez Castillo <a href="#">robjimcas@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
					<br />
					<li>
						<div class="ch-item ch-img-15-4">
							<div class="ch-info">
								<h3><spring:message code="developers.dis" /></h3>
								<p>
									Francisco José Macías García <a href="#">framacgar2@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-15-5">
							<div class="ch-info">
								<h3><spring:message code="developers.dev" /></h3>
								<p>
									Alfredo Menéndez Charlo <a href="#">alfmencha@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="ch-item ch-img-15-6">
							<div class="ch-info">
								<h3><spring:message code="developers.doc" /></h3>
								<p>
									Rubén Ramírez Vera <a href="#">rubramver@alum.us.es</a>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- why -->
