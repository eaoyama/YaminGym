<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrieve Participants</title>
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
		<div style="border-bottom-style: solid; border-radius: 10px 10px 0px 0px; border-bottom-width: 1px; border-bottom-color: #151980;">
			<h3>Retrieve Existing Participant Here</h3>
			</div>

		<form action="" method=""
			id="retrieveParticipant" style="padding-top: 2rem">

			<div class="row mb-3"><h4>Participant Retrieved</h4></div>
			
	<div class="row mb-3">
		<label for="pId" class="col-sm-3 col-form-label"
			style="text-align: right;">Participant Id</label>
		<div class="col-sm-9">
			<input type="number" class="form-control" id="pId" name="pId" value='${pId}' readonly>
		</div>
	</div>

	<div class="row mb-3">
		<label for="firstName" class="col-sm-3 col-form-label"
			style="text-align: right;">First Name</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="firstName" name="firstName" value='${firstName}' readonly>
		</div>
	</div>

	<div class="row mb-3">
		<label for="lastName" class="col-sm-3 col-form-label"
			style="text-align: right;">Last Name</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="lastName" name="lastName" value='${lastName}' readonly>
		</div>
	</div>

	<div class="row mb-3">
		<label for="startDate" class="col-sm-3 col-form-label"
			style="text-align: right;">Start Date</label>
		<div class="col-sm-9">
			<input type="date" class="form-control" id="startDate" name="startDate" value='${startDate}' readonly>
		</div>
	</div>

	<div class="row mb-3">
		<label for="batchId" class="col-sm-3 col-form-label"
			style="text-align: right;">Class</label>
		<div class="col-sm-9">
		<input type="number" class="form-control" id="batchId" name="batchId" value='${batchId}' readonly>
		</div>
	</div>


	</form>

	</div>
    
</body>
</html>