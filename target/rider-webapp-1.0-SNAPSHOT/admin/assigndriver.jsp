<%@ page import="model.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Assign Driver</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        html,
        body {
            background-color: #f5f5f5;
            height: 100%;
            margin: 0;
            padding: 0;
        }




    </style>

</head>
<body>
<main class="container">
            <div class="justify-content-center bg-dark d-flex align-items-center p-4 text-white bg-purple  shadow-sm">
                <img class="me-3" src="img/LOGO2.png" alt="" width="" height="60">
            </div>
            <div style="background-color:white;" class="px-3 py-2 border-bottom mb-3">
                <nav class="navbar navbar-light">
                    <div class="container-fluid">

                        <div class="text-end justify-content-left">
                            <ul class="nav nav-pills">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="admindashboard">Recent Bookings</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Manage Users</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="managedrivers">Manage Drivers</a></li>
                                        <li><a class="dropdown-item" href="managecustomers?page=1">List All Customers</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Generate Reports</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="dailyreport">Daily Report</a></li>
                                        <li><a class="dropdown-item" href="generatereport">Generate Driver's Daily Report</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                        <div class="d-flex">
                            <div class="flex-shrink-0 dropdown">
                                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img src="img/user.png" alt="mdo"  height="40" class="rounded-circle">
                                </a>
                                <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2" style="">
                                    <li class="dropdown-item">@${sessionScope.user.username}</li>
                                    <li class="dropdown-item"><hr class="dropdown-divider"></li>
                                    <li class="dropdown-item"><a class="dropdown-item" href="logout">Sign out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>


            <div class="container p-3 bg-body rounded shadow-sm">
                    <div class="mb-2">Booking Reference: <span class="badge bg-warning text-dark"> <c:out value="${selectedbooking.id}"/></span></div>
                        <table class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Start Address</th>
                                    <th>Destination</th>
                                    <th>Custome ID</th>
                                    <th>Fee</th>
                                    <th>Available Drivers:</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <td><fmt:formatDate type = "date" value = "${selectedbooking.date}" /></td>
                                    <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${selectedbooking.time}" /></td>
                                    <td><c:out value="${selectedbooking.start}"/></td>
                                    <td><c:out value="${selectedbooking.end}"/></td>
                                    <td><c:out value="${selectedbooking.customer_id}"/></td>
                                    <td><fmt:formatNumber value = "${selectedbooking.fee}" /></td>

                                    <form method="post"action="assigndriver">
                                        <td>
                                            <label for="isTitles">Choose a driver:</label>
                                            <select id="isTitles" name="isTitles" class="form-select form-select-sm" aria-label="Default select example" required>
                                                <c:choose>
                                                    <c:when test="${not empty alldrivers}" >
                                                        <c:forEach items="${alldrivers}" var="n">
                                                            <option value="<c:out value="${n.getID()}"/>"><c:out value="${n.getFirstName()}"/> <c:out value="${n.getLastName()}"/> - (<c:out value="${n.getReg()}"/>)</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-info mb-2 btn-sm" name="bookingid" value="<c:out value="${selectedbooking.id}"/>">Assign</button>&nbsp;
                                        </td>
                                    </form>
                                </tr>
                            </tbody>
                        </table>
            </div>






</main>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>