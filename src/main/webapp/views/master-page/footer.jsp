<%--
 * footer.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	window.onload = function () {
		var documentURL = document.URL.replace(/\?language=e[sn]/gi,'');
		var append = '';
		
		if (documentURL.includes('?')) {
			append = '&';
		} else {
			append = '?';
		}
		append = append + 'language=e';
		
	    var es = document.getElementById('es');
	    es.href = documentURL + append + 's';
	    
	    var en = document.getElementById('en');
	    en.href = documentURL + append + 'n';
	}
</script>

<div class="footer">
	<div class="container">
			<div class="col-md-3 contact-left">

				<h4>
					<spring:message code="footer.address" />
				</h4>
				<address>
					Escuela Técnica Superior de Ingeniería Informática (ETSII)<br> Universidad de
					Sevilla<br> Av. Reina Mercedes s/n, 41012 Sevilla<br>

				</address>
			</div>
			<div class="col-md-3 contact-left">

				<h4>
					<spring:message code="footer.develop" />
				</h4>
				<p>
					<span class="glyphicon" aria-hidden="true">
					<img alt="Teams" src="images/team.png" style="width:48px; border-radius: 50%;">
					</span>
					<a href="about-us/teams.do"><spring:message code="footer.developerteams" /></a>
				</p>
				<br />
				<p>
					<span class="glyphicon" aria-hidden="true">
					<img alt="Github" src="images/Github.png">
					</span>
					<a href="https://github.com/AgoraUS-G1-1617/Deliberations"> GitHub</a>
				</p>

			</div>
			<div class="col-md-3 contact-left">

				<h4>
					<spring:message code="footer.contact" />
				</h4>
				<p>
					<span class="glyphicon" aria-hidden="true">
					<img alt="Github" src="images/mail.png">
					</span>
					benavides@us.es
				</p>

			</div>
			<div class="col-md-3 contact-left">

				<h4>
					<spring:message code="footer.language" />
				</h4>
				<div>
					<span>
						<a id="es" href=""><img id="translate-flag" src="images/es.gif"/>Español</a>
						<br/><br/>
						<a id="en" href=""><img id="translate-flag" src="images/en.gif"/>English</a>
					</span>
				</div>

			</div>
			<div class="clearfix"></div>

			<div class="copyright">
				<p>© 2016 Universidad de Sevilla
			</div>
		</div>
</div>