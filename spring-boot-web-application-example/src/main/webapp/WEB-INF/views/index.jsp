<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Location Application</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header"></div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Liberty Mutual</a></li>
				<li class="active"><a href="/">ALL Records </a></li>
				<li class="active"><a href="newRecord">Create </a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<h2>Location Application Details</h2><br>
		<c:choose>
			<c:when test="${mode=='getlist'}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Code</th>
							<th>Name</th>
							<th>Type</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="loc" items="${locations}">
							<tr>
								<td>${loc.locationCode}</td>
								<td>${loc.locationName}</td>
								<td>${loc.locationType}</td>
								<td><a href="updateLocation?id=${loc.id}"
									class="glyphicon glyphicon-pencil"></a></td>
								<td><a href="deleteById?id=${loc.id}"
									class="glyphicon glyphicon-trash"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- / edit record -------------------------------------------------------->
			</c:when>
			<c:when test="${mode=='editlocation' || mode=='new'}">

				<div class="form-group">
					<form action="save" method="post">
						<input type="hidden" class="form-control" value="${location.id}"
							name="id" id="id">
						<div>
							<label for="locationCode">Code</label> <input type="text"
								class="form-control" value="${location.locationCode}"
								name="locationCode" id="locationCode">
						</div>
						<div class="form-group">
							<label for="locationName">Name</label> <input type="text"
								class="form-control" value="${location.locationName}"
								name="locationName" id="locationName">
						</div>
						<%-- <div class="form-group">
							<label for="locationType">Type</label> <input type="text"
								class="form-control" value="${location.locationType}"
								name="locationType" id="locationType">
						</div> --%>
						<div>
						
						 <label  class="radio-inline" >
                  <input type="radio" name="radioName" id="urban"  value="urban" checked="checked"/>Urban
                       </label>
                   <label  class="radio-inline">
                      <input  type="radio" name="radioName"  id="rural" value="rural"/>rural
                        </label>
						</div><br><br>
						

						<button type="submit" class="btn btn-primary">Update</button>
					</form>
				</div>

			</c:when>
		</c:choose>

	</div>

</body>
</html>
