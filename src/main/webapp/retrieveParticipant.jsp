<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Participant List</title>

<link rel="stylesheet" href="style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
	
</head>
<body>
    <jsp:include page="navBar.jsp"/>

	<div class="form-border" style="margin-top: 2rem; width:80%; padding: 2rem;">
		<div style="border-bottom-style: solid; border-radius: 10px 10px 0px 0px; border-bottom-width: 1px; border-bottom-color: #151980;">
			<h3>Searched by: ${searchCriteria}</h3>
		</div>
		
		<form style="padding-top:2rem">
			<div class="row mb-3">
			       <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Start Date</th>
                    <th>Batch Id</th>
                    <th>Class Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="participant" items="${participantList}">
                    <tr>
                        <td>${participant.pId}</td>
                        <td>${participant.firstName}</td>
                        <td>${participant.lastName}</td>
                        <td>${participant.phone}</td>
                        <td>${participant.email}</td>
                        <td>${participant.startDate}</td>
                        <td>${participant.batchId}</td>
                        <td>${participant.batchName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
			
</body>
</html>