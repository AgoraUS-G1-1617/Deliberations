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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import ="services.RankService" %>
<%@ page import ="domain.User" %>
<%@ page import ="domain.Rank" %>

<security:authentication property="principal.username" var="principalsUsername"/>

<script
	src="scripts/jquery.bootpag.min.js">
</script>

<br />
<br />
<div class="container">
	<div id="content">
		<div class="row">
			<div class="col-sm-11 col-sm-offset-1">
				<h3>
					<jstl:out value="${hilo.title}"/>
				</h3>
				
				<!-- Chunk for thread rating -->
				<jstl:set var="principalsRating" value="0" />
				<jstl:forEach var="item" items="${hilo.ratings}">
					<jstl:if test="${item.user.userAccount.username eq principalsUsername}">
						<jstl:set var="principalsRating" value="${item.rate}" />
					</jstl:if>
				</jstl:forEach>
				<div class="rating_thread_in">
				<jstl:forEach var="i" begin="1" end="5">
					<a href="rating/edit.do?threadId=${hilo.id}&value=${i}">
						<jstl:choose>
							<jstl:when test="${principalsRating != 0}">
								<jstl:choose>
									<jstl:when test="${principalsRating < i}">
										<img class="img_rate" src='images/star_n.svg' alt='*' height='20px' id='star${i}' onmouseover="updateStars(${i})"/>
									</jstl:when>
									<jstl:otherwise>
										<img class="img_rate" src='images/star.svg' alt='*' height='20px' id='star${i}' onmouseover="updateStars(${i})"/>
									</jstl:otherwise>
								</jstl:choose>
							</jstl:when>
							<jstl:otherwise>
								<img class="img_rate" src='images/star_n.svg' alt='*' height='20px' id='star${i}' onmouseover="updateStars(${i})"/>
							</jstl:otherwise>
						</jstl:choose>
					</a>
				</jstl:forEach>
				
				<a class="emerge">
					<img src="images/help.svg" style="width:20px">
					<span style="bottom:20px;left:20px;" class="threadRatingHelp"></span>
				</a>
				
				</div>
				
				<!-- ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ -->
				
				<br/>
				
				<!-- Chunk for opening and closing threads -->
				
				<security:authorize access="isAuthenticated()">
					<jstl:if test="${principalsUsername == hilo.user.userAccount.username}">
						<jstl:choose>
							<jstl:when test="${hilo.closed}">
								<a href="thread/open.do?threadId=${hilo.id}"><spring:message code="thread.open"/></a>
							</jstl:when>
							<jstl:otherwise>
								<a href="thread/close.do?threadId=${hilo.id}"><spring:message code="thread.close"/></a>
							</jstl:otherwise>
						</jstl:choose>
						
						<a class="emerge">
							<img src="images/help.svg" style="width:20px">
							<span style="bottom:20px;left:20px;" class="threadLockingHelp"></span>
						</a>
					</jstl:if>
				</security:authorize>
				
				<!-- ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ -->
			</div>
		</div>
		
		<br />
		<br />

		<!-- Mensaje inicial del hilo -->

		<jstl:if test="${p == 1}">

			<!-- /row -->
			<div class="row">
				<div class="col-sm-1">
					<div class="thumbnail">
						<a href="user/profile.do?userId=${hilo.user.id}"><img class="img-responsive user-photo"
							src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png"></a>
					</div>
					<!-- /thumbnail -->
				</div>
				<div class="col-sm-1">
				</div>
				<!-- /col-sm-1 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #286090;">
							<jstl:set var="rank" value="${rankService.calculateRank(hilo.user)}"/>
							<a href="user/profile.do?userId=${hilo.user.id}"><img class="iconRankUser" src="${rank.icon}" title="${rank.title}"></a>
							<a href="user/profile.do?userId=${hilo.user.id}"><strong class="hilo-first-font"> <jstl:out value="${hilo.user.userAccount.username }"></jstl:out></strong></a> <span class="text-muted comment-date" style="color: white;">
							<fmt:formatDate value="${hilo.creationMoment}" pattern="dd/MM/yyyy HH:mm"/></span>
						</div>
						<div class="panel-body">
							<jstl:out value="${hilo.decription}"></jstl:out>
						</div>
						<!-- /panel-body -->
					</div>
					<!-- /panel panel-default -->
				</div>
				<br />

				<!-- /col-sm-5 -->
			</div>
			<!-- /row -->
		</jstl:if>
		<!-- /Mensaje inicial del hilo -->

		<jstl:forEach items="${comments}" var="row">

			<!-- /row -->
			<div class="row">
				<div class="col-sm-1">
					<div class="thumbnail">
						<a href="user/profile.do?userId=${row.user.id}"><img class="img-responsive user-photo"
							src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png"></a>
					</div>
					<!-- /thumbnail -->
				</div>
				<!-- /col-sm-1 -->
				<div class="col-sm-1">
				<jstl:if test="${row.user.id!=loggedUserId}">
				<button onclick="location.href='karma/setKarma.do?commentId=${row.id}&value=up'" class="up-button">
				</button>
				</jstl:if>
				<div id="karma-div-${row.id}" class="karma-div">
					<button onclick="karmaDetails(${row.id},${commentsKarma[row.id][1]},${commentsKarma[row.id][2]})" class="karma-button">
						${commentsKarma[row.id][0]}
					</button>
				</div>
				<jstl:if test="${row.user.id!=loggedUserId}">
				<button onclick="location.href='karma/setKarma.do?commentId=${row.id}&value=down'" class="down-button">
				</button>
				</jstl:if>
				</div>
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<jstl:set var="rank" value="${rankService.calculateRank(row.user)}"/>
							<a href="user/profile.do?userId=${row.user.id}"><img class="iconRankUser" src="${rank.icon}" title="${rank.title}"></a>
							<a href="user/profile.do?userId=${row.user.id}"><strong> <jstl:out
									value="${row.user.userAccount.username }"></jstl:out>
							</strong></a> <span class="text-muted comment-date"><fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
							</span>
						</div>
						<div class="panel-body">
							<jstl:out value="${row.text}"></jstl:out>
						</div>
						<!-- /panel-body -->
					</div>
					<!-- /panel panel-default -->
				</div>
				<br />

				<!-- /col-sm-5 -->
			</div>
			<!-- /row -->
		</jstl:forEach>

		<jstl:if test="${hilo.closed == false}">
			<form:form action="thread/saveComment.do?p=${lastPage}" method="post"
				modelAttribute="comment">
	
				<div class="row">
					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="user" />
					<form:hidden path="creationMoment" />
					<form:hidden path="thread" />
	
					<div class="col-sm-9 col-sm-offset-2">
					<form:errors class="error" path="text" />
						<div class="input-group">
							<form:textarea rows="2" path="text" code="thread.comment"
								class="form-control noresize" />
							<span class="input-group-addon"> 
								<button type="submit" name="save" class="btn btn-primary"><spring:message code="comment.save" /></button>  
							</span>
						</div>
					</div>
				</div>
			</form:form>
		</jstl:if>

	</div>

	<div id="pagination" class="copyright"></div>
	
	<script >
        $('#pagination').bootpag({
            total: <jstl:out value="${lastPage}"></jstl:out>,
            page: <jstl:out value="${p}"></jstl:out>,
            maxVisible: 10,
            leaps: true,
            firstLastUse: true,
            first: '<',
            last: '>',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
         }).on('page', function(event, num){
             window.location.href = "thread/display.do?id="+${hilo.id}+"&p="+num+"";
         });
    </script>

</div>
<!-- /container -->
<script>

	function karmaDetails(id,positive, negative) {

		inputText = '<span class="positive-karma">'+positive+'</span><br/><span class="negative-karma">'+negative+'</span>';
		$("#karma-div-"+id).html(inputText);

	}
	
	function updateStars(id) {
		for (i = 1; i <= 5; i++) { 
		    document.getElementById('star'+i).src = 'images/star_n.svg';
		}
		
		for (i = 1; i <= id; i++) { 
		    document.getElementById('star'+i).src = 'images/star.svg';
		}
	}

	function setThreadRatingHelp(){
		language = getCookie("language");
		
		var ratingElements = document.getElementsByClassName("threadRatingHelp");
		var lockingElements = document.getElementsByClassName("threadLockingHelp");
		
		if(language=="es") {
			for(var i=0; i<ratingElements.length; i++) {
				ratingElements[i].innerHTML = 'Haciendo clic en las estrellas puedes valorar este hilo. Por defecto, se guarda \
			    tu valoración, si la hay.';
			}
			
			for(var i=0; i<lockingElements.length; i++) {
				lockingElements[i].innerHTML = 'Si ha creado este hilo y lo desea, puede cerrarlo de tal forma que ningún usuario pueda \
				comentar en él. En el futuro puede volver a abrirlo.';
			}
		} else {
			for(var i=0; i<ratingElements.length; i++) {
				ratingElements[i].innerHTML = 'By clicking on the stars you can rate this thread. By default, your rating is shown, \
			    if exists.';
			}
			
			for(var i=0; i<lockingElements.length; i++) {
				lockingElements[i].innerHTML = 'If you have created this thread and you wish so, you can lock it so that no user will \
				be able to comment on it. In the future, you can reopen it.';
			}
		}
	}

	window.onload = setThreadRatingHelp();
</script>