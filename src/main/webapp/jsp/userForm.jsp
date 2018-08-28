<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/closeAlert.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<title>User Form</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<div class="container h-50 d-flex" align="center">
		<div class="jumbotron my-auto">
			<div align="center">
				<c:if test="${param.success==true}">
					<div class="alert alert-success alert-dismissible" id="myAlert">
						<a href="#" class="close">&times;</a> <strong>Success!</strong>
						User has been added successfully.
					</div>
				</c:if>
			</div>
			<%
				String uri = request.getAttribute("javax.servlet.forward.request_uri").toString();
				String addUri = "/admin/user/addUser";
			%>

			<c:if test='<%=!uri.contains(addUri)%>'>
				<c:set var="userActionUrl" value="/admin/user/updateEdited" />
				<c:set var="actionButton" value="Update" />
			</c:if>
			<c:if test='<%=uri.contains(addUri)%>'>
				<c:set var="userActionUrl" value="/admin/user/saveUser" />
				<c:set var="actionButton" value="Add" />
			</c:if>

			<form:form modelAttribute="userForm" method="post"
				action="${userActionUrl }" cssClass="form-group">
				<form:hidden path="user_id" />
				<table id="userFormTable">
					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><form:input path="email" name="email" id="email"
								class="form-control" /></td>
						<td><form:errors path="email" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Password</form:label></td>
						<td><form:input path="password" name="password" id="password"
								class="form-control" /></td>
						<td><form:errors path="password" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="name">Name</form:label></td>
						<td><form:input path="name" name="name" id="name"
								class="form-control" /></td>
						<td><form:errors path="name" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="lastName">Last Name</form:label></td>
						<td><form:input path="lastName" name="lastName" id="lastName"
								class="form-control" /></td>
						<td><form:errors path="lastName" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="role">Role</form:label></td>
						<td><form:select path="role" class="form-control">
								<form:options items="${roleList}" itemValue="role_id"
									itemLabel="role" />
							</form:select></td>
					</tr>
				</table>
				<div style="padding-top: 15px; width: 300px;">
					<input type="submit" value="${actionButton }"
						class="btn btn-primary btn-block">
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>