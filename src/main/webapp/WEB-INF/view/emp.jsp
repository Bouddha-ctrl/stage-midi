<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">Eval</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNavDropdown">
		      <ul class="navbar-nav">
		        <li class="nav-item">
		          <a class="nav-link"  href="${pageContext.request.contextPath}/evaluation">Evaluation</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page">Employee</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/setting">Setting</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
	</header>
	<div class="container">
		<table class="table">
		<thead>
			<tr>
				<th>Matricule</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date debut</th>
				<th>role</th>
				<th >
 					<button class="btn btn-sm btn-secondary" onclick="checkAllorNone()">All</button> 
				</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${emps}">
				<tr>
					<td>${emp.matricule}</td>
					<td>${emp.nom}</td>
					<td>${emp.prenom}</td>
					<td>${emp.date_debut}</td>
					<td>${emp.role}</td>
					<td ><input name="employee" type="checkbox" value="${emp.matricule}"/></td>
					<td><c:if test = "${checkEval == 1}"><a href="${pageContext.request.contextPath}/employee/export/${emp.matricule}"><button class="btn btn-secondary" id="export" >Export</button></a></c:if></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
					<c:if test = "${checkEval == 1}"><a><button class="btn btn-secondary" onclick="getCheckedCheckboxesFor('employee')" type="submit">Export all</button></a></c:if>
	</div>
	
	
	<!-- Button trigger modal -->
	<button hidden="true" id="ModalTrigger" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
	</button>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Action failed</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        0 Employee selected
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/script.js" />"></script>
</body>
</html>