<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.simplePagination.js"></script>


<br />
<div class="container">
	<div class="row">
	<h3><spring:message code="publicProfile.title" /></h3>
		<div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
		
			<div class="well profile">
				<div class="col-sm-12">
					<div class="col-xs-12 col-sm-8 information">
						<h2 class="name">
							<jstl:out value="${user.name}"></jstl:out>
						</h2>
						<p class="user-basic-information">
							<strong>
								<spring:message code="user.genre" />
						    </strong>
						    <jstl:out value="${user.genre}"></jstl:out>
						    	
						</p>
						<p class="user-basic-information">
							<strong>
								<spring:message code="user.autonomous.community"/>:
							</strong>
							<jstl:out value=" ${user.autonomous_community}"/>							
						</p>
					</div>
					<div class="col-xs-12 col-sm-4 text-center">
						<figure>
							<img
								src="images/profile-icon.png"
								alt="" class="img-circle img-responsive">
							<figcaption class="ratings">
								<div class="karma-user-div">
								Karma: <button onclick="karmaDetails(${karmaOfUser[1]},${karmaOfUser[2]})" class="karma-button">
										${karmaOfUser[0]}
										</button> 
								</div>
							</figcaption>
						</figure>
					</div>
				</div>
				<div class="col-xs-12 divider text-center">
					<div class="col-xs-12 col-sm-4 emphasis">
						<h2>
							<strong>
								<jstl:out value=" ${threadsCreated}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.threadsCreated" />
							</small>
						</p>
					</div>
					<div class="col-xs-12 col-sm-4 emphasis">
						<h2>
							<strong>
								<jstl:out value=" ${commentsCreated}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.commentsCreated" />
							</small>
						</p>
					</div>
					<div class="col-xs-12 col-sm-4 emphasis">
						<h2>
							<strong>
								<jstl:out value=" ${commentsCreated}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.commentsCreated" />
							</small>
						</p>
					</div>
				
				</div>
				
				
				
				<div class="col-xs-12 divider text-center">
					<table style="margin: 0 auto;">
			<tr>
				<td><img src="${rank.icon}" style="width: 70%;"></td>
			</tr>
			<tr>
				<td>
					<jstl:set var="titleParts" value="${fn:split(rank.title, ' / ')}" />
					<b><jstl:out value="(${rank.number}) "/></b>
					<jstl:choose>	
    					<jstl:when test="${cookieValue=='es'}">
							<b><jstl:out value="${titleParts[1]}"/></b>	
    					</jstl:when>    
    					<jstl:otherwise>
    						<b><jstl:out value="${titleParts[0]}"/></b>		
    					</jstl:otherwise>
					</jstl:choose>
				</td>
				
			</tr>
		</table>
				
				</div>
				
				
				
				
				
			</div>
		</div>
	</div>
</div>
	
<br />
<script>

	function karmaDetails(positive, negative) {

		inputText = 'Karma: <span class="positive-karma-user">'+positive+'</span> <span class="karma-user-bar">/</span> <span class="negative-karma">'+negative+'</span>';
		$(".karma-user-div").html(inputText);

	}
</script>