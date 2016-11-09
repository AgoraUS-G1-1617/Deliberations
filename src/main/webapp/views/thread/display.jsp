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
					<jstl:out value="${hilo.title}"></jstl:out>
				</h3>
			</div>
		</div>
		
		<br />
		<br />

		<!-- Mensaje inicial del hilo -->

		<jstl:if test="${p == 1}">

			<!-- /row -->
			<div class="row">
				<div class="col-sm-1 col-sm-offset-1">
					<div class="thumbnail">
						<img class="img-responsive user-photo"
							src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
					</div>
					<!-- /thumbnail -->
				</div>
				<!-- /col-sm-1 -->

				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong> <jstl:out
									value="${hilo.user.userAccount.username }"></jstl:out>
							</strong> <span class="text-muted"><jstl:out
									value="${hilo.creationMoment}"></jstl:out></span>
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
				<div class="col-sm-1 col-sm-offset-1">
					<div class="thumbnail">
						<img class="img-responsive user-photo"
							src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
					</div>
					<!-- /thumbnail -->
				</div>
				<!-- /col-sm-1 -->

				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong> <jstl:out
									value="${row.user.userAccount.username }"></jstl:out>
							</strong> <span class="text-muted"><jstl:out
									value="${row.creationMoment}"></jstl:out></span>
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


		<%-- <display:table name="comments" id="row" requestURI="thread/display.do" --%>
		<%-- 	pagesize="5" class="table table-responsive table-striped"> --%>


		<%-- 	<spring:message var="dateHeader" code="thread.date" /> --%>
		<%-- 	<display:column title="${dateHeader}"> --%>
		<%-- 		<jstl:out value="${row.creationMoment}"></jstl:out> --%>
		<%-- 	</display:column> --%>

		<%-- 	<spring:message var="authorHeader" code="thread.author" /> --%>
		<%-- 	<display:column title="${authorHeader}"> --%>
		<%-- 		<jstl:out value="${row.user.name }"></jstl:out> --%>
		<%-- 	</display:column> --%>

		<%-- 	<spring:message var="textHeader" code="thread.text" /> --%>
		<%-- 	<display:column title="${textHeader}"> --%>
		<%-- 		<jstl:out value="${row.text }"></jstl:out> --%>
		<%-- 	</display:column> --%>

		<%-- </display:table> --%>

		<form:form action="thread/saveComment.do?p=${lastPage}" method="post"
			modelAttribute="comment">

			<div class="row">

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="user" />
				<form:hidden path="creationMoment" />
				<form:hidden path="thread" />

				<div class="col-sm-9 col-sm-offset-2">
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
