 <%--
 * login.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- <form:form action="j_spring_security_check" modelAttribute="credentials"> --%>

<%-- 	<form:label path="username"> --%>
<%-- 		<spring:message code="security.username" /> --%>
<%-- 	</form:label> --%>
<%-- 	<form:input path="username" />	 --%>
<%-- 	<form:errors class="error" path="username" /> --%>
<!-- 	<br /> -->

<%-- 	<form:label path="password"> --%>
<%-- 		<spring:message code="security.password" /> --%>
<%-- 	</form:label> --%>
<%-- 	<form:password path="password" />	 --%>
<%-- 	<form:errors class="error" path="password" /> --%>
<!-- 	<br /> -->
	
<%-- 	<jstl:if test="${showError == true}"> --%>
<!-- 		<div class="error"> -->
<%-- 			<spring:message code="security.login.failed" /> --%>
<!-- 		</div> -->
<%-- 	</jstl:if> --%>
	
<%-- 	<input type="submit" value="<spring:message code="security.login" />" /> --%>
	
<%-- </form:form> --%>


<%-- <a href="security/laws.do"><spring:message code="login.laws"></spring:message></a> --%>
<br/>
<br/>
<br/>
<script type="text/javascript" src="scripts/login.js"></script>

<link rel="stylesheet" href="styles/login.css" type="text/css">

<div class="container">    
        
    <div id="loginbox" class="mainbox col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3"> 

        
        <div class="panel panel-default" >
            <div class="panel-heading">
                <div class="panel-title text-center"></div>
            </div>     

            <div class="panel-body" >

                <form name="form" id="form" class="form-horizontal" enctype="multipart/form-data" method="POST">
                   
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="user" type="text" class="form-control" name="user" value="" placeholder="User">                                        
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                    </div>                                                                  

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i> Log in</button>                          
                        </div>
                    </div>

                </form>     

            </div>                     
        </div>  
    </div>
</div>
<br/>
<br/>
<br/>

