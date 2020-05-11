<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Category Register</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
div {
	padding-bottom: 4px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h3>WELCOME TO PRODUCT PAGE</h3>
			</div>
			<div class="card-body">
				<form:form action="insert" method="post"
					modelAttribute="productType" enctype="multipart/form-data">

					<div class="row">
						<div class="col-1"></div>
						<div class="col-3">
							<label><b>PRODUCT NAME</b></label>
						</div>
						<div class="col-4">
							<input type="text" name="prdName" class="form-control"
								placeholder="PRODUCT NAME">
						</div>
						<div class="col-4">
							<!-- FORM ERROR MESSAGE -->
						</div>
					</div>

					<div class="row">
						<div class="col-1"></div>
						<div class="col-3">
							<label><b>Choose Image</b></label>
						</div>
						<div class="col-4">
							<input type="file" name="fileUpload" class="form-control" />
						</div>
						<div class="col-4">
							<!-- FORM ERROR MESSAGE -->
						</div>
					</div>

					<div class="row">
						<div class="col-1"></div>
						<div class="col-3">
							<label><b>CATEGORY</b></label>
						</div>
						<div class="col-4">
							<select name="categoryData.categoryId" class="form-control">
								<option value="0">-SELECT-</option>
								<c:forEach items="${categoryMap}" var="entry">
								    <option value=${entry.key}>${entry.value}</option>
								</c:forEach> 
							</select>
							
						</div>
						<div class="col-4">
							<!-- FORM ERROR MESSAGE -->
						</div>
					</div>

					<div class="row">
						<div class="col-1"></div>
						<div class="col-3">
							<label><b>COLOR</b></label>
						</div>
						<div class="col-4">
							<input type="text" name="color" class="form-control"
								placeholder="COLOR">
						</div>
						<div class="col-4">
							<!-- FORM ERROR MESSAGE -->
						</div>
					</div>

					<div class="row">
						<div class="col-1"></div>
						<div class="col-3">
							<label><b>PRICE</b></label>
						</div>
						<div class="col-4">
							<input type="text" name="price" class="form-control"
								placeholder="PRICE">
						</div>
						<div class="col-4">
							<!-- FORM ERROR MESSAGE -->
						</div>
					</div>

					<div class="row text-center">
						<div class="col-4"></div>
						<div class="col-4">
							<input type="submit" value="CREATE PRODUCT"
								class="btn btn-success">
						</div>
						<div class="col-4"></div>
					</div>
				</form:form>
			</div>
			<div class="card-footer bg-info text-white text-center">
				${message}</div>
		</div>
	</div>
</body>
</html>
