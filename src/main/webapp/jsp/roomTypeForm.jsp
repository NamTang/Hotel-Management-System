<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/closeAlert.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" />

<title>Room Type Form</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<div class="container h-50 d-flex" align="center">
		<div class="jumbotron my-auto">
			<form:form modelAttribute="roomTypeForm" method="post"
				action="/admin/roomType/saveRoomType" cssClass="form-group">
				<form:hidden path="type_id" />
				<table>
					<tr>
						<td><form:label path="type" style="padding-right:10px;">Type</form:label></td>
						<td><form:input path="type" name="type" id="type"
								class="form-control" /></td>
					</tr>
					<tr>
						<td><form:label path="price">Price</form:label></td>
						<td><form:input path="price" name="price" id="price"
								class="form-control" /></td>
						<td><form:errors path="price" cssStyle="color:red;" /></td>
					</tr>
					<tr>
						<td><form:label path="number">Number</form:label></td>
						<td><form:input path="number" name="number" id="number"
								class="form-control" /></td>
						<td><form:errors path="number" cssStyle="color:red;" /></td>
					</tr>
				</table>
				<div style="padding-top: 15px; width: 300px;">
					<input type="submit" value="Save" class="btn btn-primary btn-block">
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>