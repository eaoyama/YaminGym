<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<title>User Added</title>

<link rel="stylesheet" href="style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

	
</head>
<body>
    <jsp:include page="navBar.jsp"/>

	<div class="form-border" style="margin-top: 2rem; width: 40rem; padding: 2rem;">
		<div style="border-bottom-style: solid; border-radius: 10px 10px 0px 0px; border-bottom-width: 1px; border-bottom-color: #151980;">
			<h3>${successMessage}</h3>
		</div>
		
		<form style="padding-top:2rem">
			<div class="row mb-3">
			<p> 
			User ID: ${userId}<br>
			First Name: ${userFirstName}<br>
			Last Name: ${userLastName}<br>
			Password: ${userPassword}<br>
			</p>
			
</body>
</html>