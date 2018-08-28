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
<script type="text/javascript" src="/js/images_roomType.js"></script>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/images_roomType.css" />
<title>Room Type Images</title>
</head>
<body>

	<div align="center">
		<h2>${roomType.type }</h2>
		<c:if test="${param.notImage==true}">
			<div class="alert alert-danger alert-dismissible" id="myAlert">
				<strong>Error!</strong> This file is not Image Type.
			</div>
		</c:if>
	</div>
	<form:form method="POST" action="/admin/roomType/images/uploadPicture"
		enctype="multipart/form-data">
		<input id="type_id" name="type_id" type="hidden"
			value="${roomType.type_id }" />
		<table>
			<tr>
				<td>File to upload:</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Upload" /></td>
			</tr>
		</table>
	</form:form>
	<div class="container" style="padding-top: 60px;">
		<div class="row">
			<c:forEach var="image" items="${imageList }">
				<div class="column">
					<div class="img-wrap">
						<a
							href="/admin/roomType/deleteImage/${roomType.type_id }/${image.image_id }"
							class="delete-btn">X</a> <img id="image"
							onclick="openModal(this.src)"
							src="http://localhost:8080/admin/roomType/imageDisplay?image_id=${image.image_id }">
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- Image Modal -->
	<div id="myModal" class="modal">
		<span class="close" data-dismiss="modal">&times;</span>
		<div class="modal-content">
			<img class="img-responsive" id="imageModal">
			<div class="option-action">
				<a id="btnDelete" class="delete-btn">Delete</a>
			</div>
		</div>
	</div>

	<!-- 
	<div class="modal fade" id="myModal">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<img id="imageModal" class="modal-content">
			</div>
			<div class="col-md-12 description">
				<h4>This is the first one on my Gallery</h4>
			</div>
		</div>
	</div>  -->
</body>
</html>