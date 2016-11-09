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

<%-- <jsp:useBean id="date" class="java.util.Date" /> --%>

<!-- <hr /> -->

<%-- <b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Sample Co., Inc.</b> --%>
<div class="footer">
	<div class="container">
			<div class="col-md-4 contact-left">

				<h4>
					<spring:message code="footer.address" />
				</h4>
				<address>
					Escuela Técnica Superior de Ingeniería Informática (ETSII)<br> Universidad de
					Sevilla<br> Av. Reina Mercedes s/n, 41012 Sevilla<br>

				</address>
			</div>
			<div class="col-md-4 contact-left">

				<h4>
					<spring:message code="footer.repository" />
				</h4>
				<p>
					<span class="glyphicon" aria-hidden="true">
					<img alt="Github" src="images/Github.png">
					</span>
					<a href="https://github.com/juagarfer4/Deliberations"> GitHub</a>
				</p>

			</div>
			<div class="col-md-4 contact-left">

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
			<div class="clearfix"></div>

			<div class="copyright">
				<p>© 2015 Universidad de Sevilla
			</div>
		</div>
</div>