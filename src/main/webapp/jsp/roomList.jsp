<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/search.js"></script>
<script src="/js/closeAlert.js"></script>
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<title>Room List</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<div class="container">
		<h2>Room List</h2>
		<div class="row col-md-7 ">
			<div style="margin-bottom: 20px; padding: 10px; color: white;">
				<input type="text" id="searchValue" class="form-control" />
			</div>
			<div style="padding-bottom: 5px;">
				<input type="button" onclick="location.href='/admin/room/addRoom/'"
					value="Add New Room">
			</div>
			<table id="table"
				class="table table-bordered table-hover table-responsive">
				<thead>
					<tr>
						<th scope="row">ID</th>
						<th scope="row">Floor</th>
						<th scope="row">Number</th>
						<th scope="row">Type</th>
						<th scope="row">Update</th>
						<th scope="row">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roomList }" var="room">
						<tr>
							<td>${room.room_id }</td>
							<td>${room.floor }</td>
							<td>${room.number }</td>
							<td>${room.roomType.type }</td>
							<td><input type="button"
								onclick="location.href='/admin/room/editRoom/${room.room_id }'"
								value="Update"></td>
							<td><input type="button"
								onclick="location.href='/admin/room/deleteRoom/${room.room_id }'"
								value="Delete"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>