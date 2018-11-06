<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="plunker">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>connect</title>

<script>
	document.write('<base href="' + document.location + '" />');
</script>
<script data-semver="1.2.10"
	src="http://code.angularjs.org/1.2.10/angular.js"
	data-require="angular.js@1.2.x"></script>
<script src="js/app.js" ></script>
<style>
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
</style>

</head>

<body>

	<br>
	<br>
	<br>
	<table border="0" width="100%" align="left">
		<form:form action="connect" commandName="userForm"
			enctype="multipart/form-data">
			<c:choose>
				<c:when test="${connectMessage != 'success'}">
					<tr>
						<td colspan="3">${connectMessage}</td>
					</tr>
					<tr>
						<td align="left" width="20%">Host Name:</td>
						<td align="left" width="40%"><form:input path="serverName"
								size="30" /></td>
						<td align="left"><form:errors path="serverName"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" width="20%">User Name:</td>
						<td align="left" width="40%"><form:input path="userName"
								size="30" /></td>
						<td align="left"><form:errors path="userName"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left">Password:</td>
						<td align="left"><form:password path="password" size="30" /></td>
						<td align="left"><form:errors path="password"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="Connect" name="connect" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">You have successfully connected to
							${userForm.serverName}<br>
						<br>
						</td>
					</tr>

					<tr>
						<td>Please select a file</td>
						<td><input type="file" name="file" /></td>
						<td></td>

					</tr>
					<tr>
						<td></td>
						<td>(OR)</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">Enter Commands</td>
						<td align="left">
							<div ng-controller="MyCtrl">
								<form:textarea path="shellCommandParameter" ng-model="someInput"
									cols="55"></form:textarea>
								<img src="images/add.png" ng-click="add()">
							</div>
						</td>
					</tr>
					<tr>
						<td></td>

						<td><textarea my-Text="" name="shellCommand" rows="10"
								cols="60"></textarea></td>
						<td></td>
					</tr>

					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="Execute" name="executeCommand"></td>
						<td></td>
					</tr>
					<tr></tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left" colspan="3"><br>
						<br>
							<table border="1" width="100%" bgcolor="pink">
								<tr>
									<td></td>
									<td></td>
									<td>
										<!-- <a href ="./connect?params=downloadPDF">Download File</a>-->
										<input type="submit" value="Download" name="downloadPDF">
									</td>
								</tr>
								<tr>
									<td>Command</td>
									<td>Output</td>
									<td>Result</td>
								</tr>
								<c:forEach items="${userForm.listOfOutput}" var="displayObj">
									<tr>
										<td>${displayObj.command}</td>
										<td>${displayObj.output}</td>
										<td>${displayObj.result}</td>
									</tr>
								</c:forEach>
							</table></td>
					</tr>
					<form:hidden path="userName" />
					<form:hidden path="serverName" />
					<form:hidden path="password" />
				</c:otherwise>

			</c:choose>

		</form:form>
	</table>

</body>
</html>