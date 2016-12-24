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
								<spring:message code="user.genre" />:
						    </strong>
						    <jstl:out value="${user.genre}"></jstl:out>
						    	
						</p>
						<p class="user-basic-information">
							<strong>
								<spring:message code="user.email"/>:
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
								<div class="karma-user-div">
								Karma: <button onclick="karmaDetails(${karmaOfUser[1]},${karmaOfUser[2]})" class="karma-button">
										${karmaOfUser[0]}
										</button> 
								</div>
								<a class="emerge">
									<img src="images/help.svg" style="width:20px">
									<span style="bottom:20px;left:20px;" id="karmaHelp"></span>
								</a>
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
								<jstl:out value=" ${ratingsCreated}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.ratingsCreated" />
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
					<jstl:set var="titleParts" value="${fn:split(rank.title, ' / ')}" />
					<div class="spanishRankDescription">
						<b><jstl:out value="(${rank.number}) "/><jstl:out value="${titleParts[1]}: "/></b>	
	   					<jstl:out value="${rank.descriptionEs}"/>
	   				</div>
	   				<div class="englishRankDescription">
						<b><jstl:out value="(${rank.number}) "/><jstl:out value="${titleParts[0]}: "/></b>		
	   					<jstl:out value="${rank.descriptionEn}"/>
   					</div>
				</td>
				<%-- Siguientes rangos, para cada uno de ellos ventana emergente --%>
				<!-- License: Flaticon Basic License. By Madebyoliver(http://www.flaticon.com/authors/madebyoliver) -->
					<jstl:forEach items="${nextRanks}" var="rankTemp">
						<jstl:set var="titleTempParts" value="${fn:split(rankTemp.title, ' / ')}" />
						<td><a class="emerge"><img class="darkRank" src="${rankTemp.icon}">
							<span >
							<img class="iconCenter" src="${rankTemp.icon}">
							
							<div class="spanishRankDescription">
								<b><jstl:out value="(${rankTemp.number}) "/><jstl:out value="${titleTempParts[1]}: "/></b>	
								<jstl:out value=" ${rank.descriptionEs}" />
							</div>
							<div class="englishRankDescription">
								<b><jstl:out value="(${rankTemp.number}) "/><jstl:out value="${titleTempParts[0]}: "/></b>
								<jstl:out value=" ${rank.descriptionEn}" />
							</div>
							
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
<script>
	function karmaDetails(positive, negative) {
		inputText = 'Karma: <span class="positive-karma-user">'+positive+'</span> <span class="karma-user-bar">/</span> <span class="negative-karma">'+negative+'</span>';
		$(".karma-user-div").html(inputText);
	}
	
	function setRankDescriptions(){
		language = getCookie("language");
		
		if (language == "es") {
			var elements = document.getElementsByClassName("englishRankDescription");
			for(var i=0; i<elements.length; i++) {
			    elements[i].innerHTML = '';
			}
			
			document.getElementById("karmaHelp").innerHTML = "Todos los usuarios tienen un karma asociado, que consiste \
			en el balance de votos positivos y negativos que ha recibido en sus comentarios por parte de otros usuarios."
		} else {
			var elements = document.getElementsByClassName("spanishRankDescription");
			for(var i=0; i<elements.length; i++) {
			    elements[i].innerHTML = '';
			}
			
			document.getElementById("karmaHelp").innerHTML = "All users have a certain karma level associated, which consists of \
			the number of positive and negative votes that have been received in his or her comments by other users."
		}
	}
	
	window.onload = setRankDescriptions();
</script>