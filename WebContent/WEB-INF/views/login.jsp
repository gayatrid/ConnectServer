<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
   <!--Made with love by Mutiullah Samim -->
   
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="css/logg.css">
	<style type="text/css">
.error {
	color: red;
	font-weight: bold;
}

.successStatus {
	color: green;
	font-weight: bold;
}

.failStatus {
	color: red;
	font-weight: bold;
}

input {
	border-radius: 7px;
	border-width: thin;
	border-color: coral;
}

</style>

	
</head>
<body bgcolor="light blue">
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Connect Server</h3>
				<div class="d-flex justify-content-end social_icon">
					
				</div>
			</div>
			<div class="card-body">
				<form:form action="connect" commandName="userForm"		enctype="multipart/form-data">
				<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="serverName" class="form-control" placeholder="ServerName" value="sjgemappdevn05"/>
						<%-- <form:errors path="serverName" cssClass="error" />  --%>
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<form:input path="userName" class="form-control" placeholder="username" value="sgovada"/>
						<%-- <form:errors path="userName" cssClass="error" /> --%>
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<form:password path="password" class="form-control" placeholder="password" value="Welc0me$123"/>
						<%-- <form:errors path="password"  cssClass="error" /> --%>
					
					</div>
						<div class="form-group" style="color:white">
						<input type="checkbox" name="sudoOracle" value="sudoOracle">&nbsp;Sudo Oracle<br>
					</div>
					
					</div>
									<div class="form-group">
						<input type="submit" value="Connect" name="connect" class="btn float-right login_btn">
					</div>
					
					<div>
						<c:if test="${not empty listOfOutput}"/>
						<label class="failStatus"><c:out  value="${connectMessage}"/></label>
					</div>
				</form:form>
			</div>
		
		</div>
	</div>
</div>
</body>
</html>
							

