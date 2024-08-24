<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Navigation Bar</title>

<link rel="stylesheet" href="style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
	
	<!-- Bootstrap JS (necessary for dropdowns to work) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
	
	
</head>
<body>

<nav class="navbar navbar-expand-lg sticky-top bg-body-tertiary">
    <div class="container-fluid">
        <div style="margin: 0rem 2rem 0rem 0rem;">
            <img src="YaminGymLogo.png" alt="Yamin Gym Logo" width="200px" height="auto">
        </div>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a href="dashboard.jsp" class="nav-link">Dashboard</a></li>

                <!-- Participant Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="participantDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">Participants</a>
                    <ul class="dropdown-menu" aria-labelledby="participantDropdown">
                        <li><a class="dropdown-item" href="addParticipant.html">Add Participant</a></li>
                        <li><a class="dropdown-item" href="RetrieveParticipantList">Retrieve Participant List</a></li>
                        <li><a class="dropdown-item" href="retrieveParticipant.html">Retrieve Participant</a></li>
                        <li><a class="dropdown-item" href="updateParticipant.html">Update Participant</a></li>
                        <li><a class="dropdown-item" href="deleteParticipant.html">Delete Participant</a></li>
                    </ul>
                </li>

                <!-- Batch Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="batchDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">Classes</a>
                    <ul class="dropdown-menu" aria-labelledby="batchDropdown">
                        <li><a class="dropdown-item" href="addBatch.html">Add Class</a></li>
                        <li><a class="dropdown-item" href="RetrieveBatchList">Retrieve Class List</a></li>
                        <li><a class="dropdown-item" href="retrieveBatch.html">Retrieve Class</a></li>
                        <li><a class="dropdown-item" href="updateBatch.html">Update Class</a></li>
                        <li><a class="dropdown-item" href="deleteBatch.html">Delete Class</a></li>
                    </ul>
                </li>

                <!-- User Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">Users</a>
                    <ul class="dropdown-menu" aria-labelledby="userDropdown">
                        <li><a class="dropdown-item" href="addUser.html">Add User</a></li>
                        <li><a class="dropdown-item" href="RetrieveUserList">Retrieve User List</a></li>
                        <li><a class="dropdown-item" href="updateUser.html">Update User</a></li>
                        <li><a class="dropdown-item" href="deleteUser.html">Delete User</a></li>
                    </ul>
                </li>
            </ul>

            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="button button_secondary" type="submit">Search</button>
            </form>

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a href="index.html" class="nav-link" style="margin-left: 4rem;">Sign out</a></li>
            </ul>
        </div>
    </div>
</nav>


</body>
</html>