<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Location Application</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">List</a></li>
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <h2>Location Application Details</h2>       
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Location-Code</th>
        <th>Location-Name</th>
        <th>Location-Type</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="loc" items="${locations}">
      <tr>
        <td>${loc.locationCode}</td>
        <td>${loc.locationName}</td>
        <td>${loc.locationType}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
