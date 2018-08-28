<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/search.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" />

<title>User List</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<div class="container">
		<h2>User List</h2>
		<div class="row col-md-7 ">
			<div style="margin-bottom: 20px; padding: 10px; color: white;">
				<input type="text" id="searchValue" class="form-control" />
			</div>
			<div style="padding-bottom: 5px;">
				<input type="button" onclick="location.href='/admin/user/addUser/'"
					value="Add New User">
			</div>
			<table id="table"
				class="table table-bordered table-hover table-responsive">
				<thead>
					<tr>
						<th scope="row">ID</th>
						<th scope="row">Email</th>
						<th scope="row">Password</th>
						<th scope="row">First Name</th>
						<th scope="row">Last Name</th>
						<th scope="row">Role</th>
						<th scope="row">Active</th>
						<th scope="row">Update</th>
						<th scope="row">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList }" var="user">
						<tr>
							<td>${user.user_id }</td>
							<td>${user.email }</td>
							<td>${user.password }</td>
							<td>${user.name }</td>
							<td>${user.lastName }</td>
							<td>${user.role.role }</td>
							<td>${user.active }</td>
							<td><input type="button"
								onclick="location.href='/admin/user/editUser/${user.user_id }'"
								value="Update"></td>
							<td><input type="button"
								onclick="location.href='/admin/user/deleteUser/${user.user_id }'"
								value="Delete"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>