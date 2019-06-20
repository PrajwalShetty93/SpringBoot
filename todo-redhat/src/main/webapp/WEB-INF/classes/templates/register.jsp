<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link
	href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css"
	rel="stylesheet" crossorigin="anonymous">
<body>
	<form:form id="regForm" class="form-signin" modelAttribute="user"
		action="/processRegistration" method="post">

		<table align="center">
			<c:if test="${not empty errorMsg}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
			</c:if>
			<tr>
				<td><h2 class="form-signin-heading">Please</h2></td>
				<td><h2 class="form-signin-heading">Register</h2></td>
			</tr>
			<tr>
				<td><p>User Name</p></td>
				<td><form:input class="form-control" required=""
						path="username" placeholder="username" name="username"
						id="username" /></td>
				<td><form:errors path="username"  />
			</tr>
			<tr>
				<td><p>Password</p></td>
				<td><form:password class="form-control" required=""
						path="password" placeholder="Password" name="password"
						id="password" /></td>
			</tr>
			<tr>
				<td><p>First Name</p></td>
				<td><form:input class="form-control" required=""
						path="firstname" placeholder="First Name" name="firstname"
						id="firstname" /></td>
				<td><form:errors path="firstname" cssClass="error" />
			</tr>
			<tr>
				<%-- <td><form:label cla path="lastname">LastName</form:label></td> --%>
				<td><p>Last Name</p></td>
				<td><form:input class="form-control" required=""
						path="lastname" placeholder="Last Name" name="lastname"
						id="lastname" /></td>
			</tr>
			<tr>
				<td><p>Email Id</p></td>
				<td><form:input class="form-control" required="" path="emailid"
						placeholder="Email-Id" name="emailid" id="emailid" /></td>
			</tr>
			<p>
			<tr>
				<td><form:label path="role">Role</form:label></td>
				<td><form:select path="role" class="form-control">
						<form:option value="NONE" label="Select Role" />
						<form:options items="${rolesList}" />
					</form:select></td>
			</tr>
			</p>

			<tr>
				<td></td>
				<td><form:button id="register"
						class="btn btn-lg btn-primary btn-block" type="submit">Register</form:button>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td><a href="/login">Back</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>