<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrieve Class</title>

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
			<h3>Retrieve Existing Class Here</h3>
			</div>

		<form action="RetrieveParticipantServlet" method="post"
			id="retrieveParticipant" style="padding-top: 2rem">

			<div class="row mb-3"><h4>Class Retrieved</h4></div>
			
	<div class="row mb-3">
		<label for="batchId" class="col-sm-3 col-form-label"
			style="text-align: right;">Batch Id</label>
		<div class="col-sm-9">
			<input type="number" class="form-control" id="batchId" name="batchId" value="${batchId}" readonly>
		</div>
	</div>

			<div class="row mb-3">
				<label for="batchName" class="col-sm-3 col-form-label" style="text-align:right;">Class Name</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="batchName" name="batchName" value="${batchName}" readonly >
				</div>
			</div>

            <div class="row mb-3">
				<label for="dayOfClass" class="col-sm-3 col-form-label" style="text-align:right;">Class held on</label>
				<div class="col-sm-9">
				<input type="text" class="form-control" id="dayOfClass" name="dayOfClass" value="${dayOfClass}" readonly>
				</div>
			</div>

			<div class="row mb-3">
				<label for="startHour" class="col-sm-3 col-form-label" style="text-align:right;">Class start time</label>
				<div class="col-sm-9">
					<input type="time" class="form-control" id="startHour" name="startHour" value="${startHour}" readonly>
				</div>
			</div>

	</form>

	</div>

</body>
</html>