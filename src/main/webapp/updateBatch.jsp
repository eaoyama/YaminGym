<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Batch Updated</title>

<link rel="stylesheet" href="style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
	
</head>
<body>
    <jsp:include page="navBar.jsp"/>
    
    	<div class="form-border" style="margin-top: 2rem; width: 80%; padding: 2rem;">
		<div style="border-bottom-style: solid; border-radius: 10px 10px 0px 0px; border-bottom-width: 1px; border-bottom-color: #151980;">
			<h3>${successMessage}</h3>
		</div>
		
		<form style="padding-top:2rem">
			<div class="row mb-3">
			<p> 
			Batch ID: ${batchId}<br>
			Batch Name: ${batchName}<br>
			Class Held On: ${dayOfClass}<br>
			Class Start Time: ${startHour}
			</p>
</body>
</html>