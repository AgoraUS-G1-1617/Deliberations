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

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.simplePagination.js"></script>


<div class="container">
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
			<div class="well profile">
				<div class="col-sm-12">
					<div class="col-xs-12 col-sm-8 information">
						<h2 class="name">
							<jstl:out value="${user.name}"></jstl:out>
						</h2>
						<p class="user-basic-information">
							<strong>
								<spring:message code="customer.genre" />
						    </strong>
						    <jstl:out value="${user.genre}"></jstl:out>
						    	
						</p>
						<p class="user-basic-information">
							<strong>
								<spring:message code="customer.email"/>:
							</strong>
							<jstl:out value=" ${user.email}"/>							
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
								<p>
									Karma <a href="#"> <span class="fa fa-star"></span>
									</a> <a href="#"> <span class="fa fa-star"></span>
									</a> <a href="#"> <span class="fa fa-star"></span>
									</a> <a href="#"> <span class="fa fa-star"></span>
									</a> <a href="#"> <span class="fa fa-star-o"></span>
									</a>
								</p>
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
					<div class="col-xs-12 col-sm-4 emphasis">
						<h2>
							<strong>
								<jstl:out value=" ${messagesReceived}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.messagesReceived" />
							</small>
						</p>
					</div>
					<div class="col-xs-12 col-sm-4 emphasis">
						<h2>
							<strong>
								<jstl:out value=" ${messagesSent}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.messagesSent" />
							</small>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class="container">
	
	<br />
	<div class="thumbnail">

		<table style="margin: 0 auto;">
			<tr>
				<td><img src="${rank.icon}"></td>
				<td style='padding: 3%' WIDTH="500">
					<b><jstl:out value=" ${rank.title}"/><jstl:out value=" (${rank.number})"/></b>
					<jstl:choose>
    					<jstl:when test="${cookieValue=='es'}">
        					${rank.descriptionEs}
    					</jstl:when>    
    					<jstl:otherwise>
        					${rank.descriptionEn}
    					</jstl:otherwise>
					</jstl:choose>
				</td>
				<%-- Siguientes rangos, para cada uno de ellos ventana emergente --%>
				<!-- License: Flaticon Basic License. By Madebyoliver(http://www.flaticon.com/authors/madebyoliver) -->
					<jstl:forEach items="${nextRanks}" var="rankTemp">
						<td><a class="emerge"><img class="darkRank" src="${rankTemp.icon}">
							<span >
							<img class="iconCenter" src="${rankTemp.icon}">
							<br />
							<b>${rankTemp.title} (${rankTemp.number})</b>
							<br />
							<jstl:choose>
								<jstl:when test="${cookieValue=='es'}">
									<p>
										<jstl:out value=" ${rank.descriptionEs}" />
									</p>
								</jstl:when>
								<jstl:otherwise>
									<p>
										<jstl:out value=" ${rank.descriptionEn}" />
									</p>
								</jstl:otherwise>
							</jstl:choose>

						</span>
						</a></td>
						<jstl:if test="${rankTemp.number != numRanks-1}">
							<td><img src="images/rightIcon.png"></td>
						</jstl:if>

					</jstl:forEach>
			</tr>
		</table>
	</div>

</div>
<br />