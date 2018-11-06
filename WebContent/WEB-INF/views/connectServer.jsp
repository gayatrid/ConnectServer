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
<script data-semver="1.2.10"	src="http://code.angularjs.org/1.2.10/angular.js"	data-require="angular.js@1.2.x"></script>
<link href="css/login.css">
<link href="css/form.css">
<script src="js/app.js"></script>
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
table {
    padding:20px
}

td {
    padding: 8px;
}
</style>

<script type="text/javascript">
//auto expand textarea
function adjust_textarea(h) {
    h.style.height = "20px";
    h.style.height = (h.scrollHeight)+"px";
}
</script>


</head>

<body onload="c()" bgcolor="moccasin">
<br>
<br>
<br>


<table bgcolor="moccasin" align="left" >
	<form:form  class="form-style-1"  action="connect" commandName="userForm" enctype="multipart/form-data">
	
	 <c:choose>
    <c:when test="${userForm.pageContent == 'ImportCert'}">
    <tr>
				<td colspan="3">Import Certificate To KeyStore <br> <br>
				</td>
			</tr>
      		 <tr>
				<td>Select Certificate</td>
				<td><input type="file" name="file" /></td>
				<td></td>
			</tr>
			<tr>
				<td>Select KeyStroe</td>
				<td><select name="keyStore">
				    <option value="0">Select Keystore:</option>
				    <option value="DemoKey">Demo Key Store</option>
				    <option value="DemoTrust">Demo Trust Store</option>
				    <option value="cacert">Java KeyStore</option>
				    <option value="b2bkeystore">B2B keyStore</option>
			  </select>&nbsp(or) JKS File Path</td> <td><input type="text" name="customeKeyStore" style="width:400px" value="/gemdata/scripts/issuereport/Platform/Certificate/DemoTrust.jks"/></td>
				<td></td>
			</tr>
			<tr><td>Alias</td><td><input type="text" name="alias" value="codecom"/></td></tr>
			<tr><td>Password(JKS)</td><td><input type="password" name="passphrase" value="DemoTrustKeyStorePassPhrase"/></td></tr>
			<tr>
				<td align="center" colspan="2">
				<input type="submit"	value="Import" name="importAction"></td>
				<td></td>
			</tr>
    </c:when>
</c:choose>
<div style="background-color:LightGreen">${result}</div>
 <c:choose>
    <c:when test="${userForm.pageContent == 'listkeystore'}">
<table border="1" width="100%" bgcolor="pink">
			<c:forEach items="${userForm.listOfOutput}" var="displayObj">
					<td>${displayObj.output}</td>
			</c:forEach>
		</table>
		    </c:when>
</c:choose>


	<form:hidden path="userName" />
	<form:hidden path="serverName" />
	<form:hidden path="password" />
	<form:hidden path="pageContent"/>


</form:form>
</table>
	</div>
</div>
</body>
</html>