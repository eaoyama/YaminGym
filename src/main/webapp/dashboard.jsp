<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" href="style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
	
</head>
<body>
    <jsp:include page="navBar.jsp"/>


	<div class="form-border"
		style="margin-top: 2rem; width: 40rem; padding: 2rem;">
		<div
			style="border-bottom-style: solid; border-radius: 10px 10px 0px 0px; border-bottom-width: 1px; border-bottom-color: #151980;">
			
			<% if (request.getAttribute("successMessage") != null) { %>
			<h3>Welcome, ${userFirstName} ${userLastName}.</h3>
			<% } %>			
		</div>
		<p>
		<h3>${successMessage}</h3>
		</p>


	</div>
</body>
</html>