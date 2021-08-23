<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>setting</title>
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
		          <a class="nav-link "  href="${pageContext.request.contextPath}/evaluation">Evaluation</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/employee">Employee</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="#">Setting</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
	</header>
	<div class="container mt-3">
	
		<c:if test = "${error != null}">
			<div class="alert alert-danger" role="alert">
			  ${error}
			</div>
		</c:if>
	
		<form class="row g-3" enctype="multipart/form-data" method="POST" action="${pageContext.request.contextPath}/setting/add">
			<div class="input-group mb-2">
			  <label class="input-group-text" >Criteres</label>
			  <input type="file" id="CritExcel" name="CritExcel" class="form-control form-control-sm"/>
			</div>
			
			<div class="input-group mb-3">
			  <label class="input-group-text" >Employees</label>
			  <input type="file" id="EmpExcel" name="EmpExcel" class="form-control form-control-sm"/>
			</div>	
			<div class="col-auto">
				<button class="btn btn-primary" type="submit">Ajouter</button>
			</div>	
		</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>