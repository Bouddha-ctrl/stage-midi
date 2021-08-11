<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">Eval</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNavDropdown">
		      <ul class="navbar-nav">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" >Evaluation</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/employee">Employee</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/setting">Setting</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
	</header>
<div class="container ">
	<table class="table align-middle" style="text-align:center;">
		<thead>
		<tr >
			<th scope="col">Role</th>
			<th scope="col">Critere</th>
			<th scope="col">Taux Critere</th>
			<th scope="col">Sous-critere</th>
			
			<th scope="col">Taux sous-crit</th>
		</tr>	
		</thead>
		<tbody>
			<c:forEach var="obj" items="${evaluation.getRoles()}">
				<tr  class="table-danger">
					<td rowspan="${obj.scritCount()}">${obj.nom}</td>
					<td class="table-warning" rowspan="${obj.criteres.size()}">${obj.criteres.get(0).nom}</td>
					<td class="table-warning" rowspan="${obj.criteres.size()}"><fmt:formatNumber type = "percent" 
         										maxIntegerDigits="3" value = "${obj.criteres.get(0).taux}" /> </td>
					<td class="table-secondary">${obj.criteres.get(0).scriteres.get(0).nom}</td>
					<td class="table-secondary"><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${obj.criteres.get(0).scriteres.get(0).taux}"/></td>
				</tr>
				<c:forEach var="crit" items="${obj.criteres}">
					<c:if test = "${crit != obj.criteres.get(0)}">
			         	<tr >
							<td class="table-warning" rowspan="${crit.scriteres.size()}">${crit.nom}</td>
							<td class="table-warning" rowspan="${crit.scriteres.size()}"><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${crit.taux}" /></td>
							<td class="table-secondary">${crit.scriteres.get(0).nom}</td><td class="table-secondary"><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${crit.scriteres.get(0).taux}" /></td>
						</tr>
			      	</c:if>
					
					<c:forEach var="scrit" begin="1" items="${crit.scriteres}">
						<tr class="table-secondary">
							<td>${scrit.nom}</td><td><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${scrit.taux}" /></td>
						</tr>
				 	</c:forEach>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>