<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link rel="stylesheet" href="styles/thread_style.css" media="screen"
	type="text/css" />
<script src="scripts/jquery.bootpag.min.js"></script>

<div class="container">
	<h3 class="title-forum">
		<spring:message code="thread.head" />
	</h3>

	<div id="create-button" style="text-align: right">
		<a href="thread/create.do" class="btn btn-primary" role="button"><span
			class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <spring:message
				code="list.new" /></a>
	</div>
	<div id="bbpress-forums">
		<ul id="forums-list-0" class="bbp-forums" style="padding-right: 40px;">
			<li class="bbp-header">
				<ul class="forum-titles">
					<li class="bbp-forum-info"><spring:message code="thread.title" /></li>
					<li class="bbp-forum-topic-messages"><spring:message code="thread.answers" /></li>
					<li class="bbp-forum-topic-rating"></li>
					<li class="bbp-forum-freshness"><spring:message code="thread.lastComment" /></li>
				</ul>
			</li>
			<jstl:forEach items="${threads}" var="threadRow">
				<li class="bbp-body">
					<ul id="bbp-forum-6"
						class="loop-item-0 odd bbp-forum-status-open bbp-forum-visibility-publish post-6 forum type-forum status-publish hentry">
						<li class="bbp-forum-info">
							
							<a class="bbp-forum-title" href="thread/display.do?id=${threadRow.id}&p=1">
							<jstl:out value="${threadRow.title }"/>
							</a>
							<!-- Chunk for closed threads -->
							<jstl:if test="${threadRow.closed}">
				  				<span id="lock-icon" class="glyphicon glyphicon-lock" aria-hidden="true" ></span>
							</jstl:if>
							<!-- ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^  -->
							<security:authorize  access="isAuthenticated()">
								<jstl:if test="${threadRow.user.id == actUserId && !threadRow.closed}">
									&nbsp 
									(<a href="thread/edit.do?threadId=${threadRow.id}">
											<spring:message code="thread.edit" />
									</a>)
								</jstl:if>
							</security:authorize>
							
							<div class="bbp-forum-content">
								<spring:message code="thread.created" />
								<a href="user/profile.do?userId=${threadRow.user.id}">
									<jstl:out value="${threadRow.user.name }"/>
								</a> 
								<fmt:formatDate value="${threadRow.creationMoment}" pattern="dd/MM/yyyy"/>
								
							</div>
							
						</li>
						
						<li class="bbp-forum-topic-messages">
							<span class="badge"> 
								<jstl:out value="${threadRow.comments.size()}"/>
							</span>
						</li>
						<li class="bbp-forum-topic-rating">
							<jstl:forEach var="i" begin="1" end="${threadRow.rating}">
								
								<img src='images/star.svg' alt='*' height='20px' />
							
							</jstl:forEach> <jstl:forEach var="i" begin="${threadRow.rating}" end="4">
								
								<img src='images/star_n.svg' alt='*' height='20px' />
							
							</jstl:forEach>
						</li>
						<li class="bbp-forum-freshness">
							<jstl:if test="${threadRow.lastComment!=null}">
								<fmt:formatDate value="${threadRow.lastComment.creationMoment}" pattern="dd/MM/yyyy HH:mm"/>
							</jstl:if>						
						</li>
					</ul>
				</li>
			</jstl:forEach>

		</ul>
	</div>
</div>


<div id="pagination" class="copyright">

	<script>
		$('#pagination').bootpag({
			total : <jstl:out value="${total_pages}"></jstl:out>,
			page : <jstl:out value="${p}"></jstl:out>,
			maxVisible : 5,
			leaps : true,
			firstLastUse : true,
			first : '<',
	        last: '>',
			wrapClass : 'pagination',
			activeClass : 'active',
			disabledClass : 'disabled',
			nextClass : 'next',
			prevClass : 'prev',
			lastClass : 'last',
			firstClass : 'first'
		}).on('page', function(event, num) {
			window.location.href = "thread/list.do?page=" + num + "";
			page = 1
		});
	</script>

</div>