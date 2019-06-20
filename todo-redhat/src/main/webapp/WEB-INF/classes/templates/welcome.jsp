<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link
	href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css"
	rel="stylesheet" crossorigin="anonymous">

	
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="text-align: center; background-color:lightblue;" id="#hideMe"><c:if test="${not empty deleteMsg}">${deleteMsg}</c:if></div>
	<div style="text-align: center"><a href="/getAllUsers" align="center" class="btn btn-default">View All Users</a></div>
	<c:if test="${not empty userList}">
		<div class="container">
			<table class="table table-striped">
				<b>User Details:</b>
				<thead>
					<tr class="tr tr-success">
						<td>User Name</td>
						<td>First Name</td>
						<td>Last Name</td>
						<td>Email Id</td>
						<td>Role</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.firstname}</td>
							<td>${user.lastname}</td>
							<td>${user.emailid}</td>
							<td>${user.role}</td>
							<c:if test="${not empty role}">
								<td><a class="btn btn-info"
									href="/update-person?id=${user.username}">Update</a> <a
									class="btn btn-danger"
									onclick="return confirm('Are you sure you want to delete?')"
									href="/delete-person?id=${user.username}">Delete</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<div style="text-align: right"><a href="/logout" class="btn btn-default">Logout</a></div>
</body>
</html>