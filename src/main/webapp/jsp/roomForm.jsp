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

<title>Room Form</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<div class="container h-50 d-flex" align="center">
		<div class="jumbotron my-auto">

			<c:if test="${param.success==true}">
				<div class="alert alert-success alert-dismissible" id="myAlert">
					<a href="#" class="close">&times;</a> <strong>Success!</strong>
					Room has been added successfully.
				</div>
			</c:if>
			<c:if test="${param.error==true}">
				<div class="alert alert-danger alert-dismissible" id="myAlert">
					<a href="#" class="close">&times;</a> <strong>Error!</strong> This
					room already exists.
				</div>
			</c:if>

			<%
				String uri = request.getAttribute("javax.servlet.forward.request_uri").toString();
				String addUri = "/admin/room/addRoom";
			%>

			<c:if test='<%=!uri.contains(addUri)%>'>
				<c:set var="userActionUrl" value="/admin/room/updateEdited" />
				<c:set var="actionButton" value="Update" />

			</c:if>
			<c:if test='<%=uri.contains(addUri)%>'>
				<c:set var="userActionUrl" value="/admin/room/saveRoom" />
				<c:set var="actionButton" value="Add" />
			</c:if>

			<form:form modelAttribute="roomForm" method="post"
				action="${userActionUrl }" cssClass="form-group">
				<form:hidden path="room_id" />
				<table>
					<tr>
						<td><form:label path="floor">Floor</form:label></td>
						<td><form:input path="floor" name="floor" id="floor"
								class="form-control" /></td>
						<td><form:errors path="floor" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="number">Number</form:label></td>
						<td><form:input path="number" name="number" id="number"
								class="form-control" /></td>
						<td><form:errors path="number" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="roomType">Type</form:label></td>
						<td><form:select path="roomType" class="form-control">
								<form:options items="${roomTypeList}" itemValue="type_id"
									itemLabel="type" />
							</form:select></td>
					</tr>
					<tr>
						<td><form:label path="roomstatus">Status</form:label></td>
						<td><form:select path="roomstatus" class="form-control">
								<form:option value="Available">Available</form:option>
								<form:option value="Booked">Booked</form:option>
								<form:option value="UnderConstruction">Under Construction</form:option>
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