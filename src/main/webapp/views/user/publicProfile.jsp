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
						<p class="user-basic-information">
							<br>
							<a href="message/user/create.do?username=${user.userAccount.username}">
								<i class="material-icons">mail</i>
								<spring:message code="user.sendpm"/>
							</a>
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
								<jstl:out value=" ${ratingsCreated}" />
							</strong>
						</h2>
						<p>
							<small>
								<spring:message code="user.ratingsCreated" />
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
					<div class="spanishRankDescription">
						<b><jstl:out value="(${rank.number}) "/><jstl:out value="${titleParts[1]}"/></b>	
	   				</div>
	   				<div class="englishRankDescription">
						<b><jstl:out value="(${rank.number}) "/><jstl:out value="${titleParts[0]}"/></b>		
   					</div>
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
	
	function getCookie(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length,c.length);
	        }
	    }
	    return "";
	}
	
	function setRankDescriptions(){
		language = getCookie("language");
		
		if (language == "es") {
			var elements = document.getElementsByClassName("englishRankDescription");
			for(var i=0; i<elements.length; i++) {
			    elements[i].innerHTML = '';
			}
		} else {
			var elements = document.getElementsByClassName("spanishRankDescription");
			for(var i=0; i<elements.length; i++) {
			    elements[i].innerHTML = '';
			}
		}
	}
	
	window.onload = setRankDescriptions()
</script>