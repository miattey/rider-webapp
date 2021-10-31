<%@ page import="model.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rider - Dashboard</title>

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
                            <a class="nav-link " aria-current="page" href="admindashboard">Recent Bookings</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle " data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Manage Users</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="managedrivers">Manage Drivers</a></li>
                                <li><a class="dropdown-item" href="managecustomers?page=1">List All Customers</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle active" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Generate Reports</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Daily Report</a></li>
                                <li><a class="dropdown-item" href="#">Generate Driver's Daily Report</a></li>
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



    <div class="container mt-3">
        <div class="row">
            <div class="bg-light p-3 border-bottom shadow-sm align-middle">
                <h1 class="h4 mb-2 fw-normal ">DAILY BOOKINGS OVERVIEW</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 p-3 bg-body shadow-sm">
                <!-- Title -->
                <h6 class="text-uppercase text-muted mb-2">
                    DATE
                </h6>

                <!-- Heading -->
                <span class="h2 mb-0">
                    <c:out value="${date}" />
                    </span>

                <!-- Badge -->

            </div>
            <div class="col-md-6 p-3 bg-body shadow-sm">
                <!-- Title -->
                <h6 class="text-uppercase text-muted mb-2">
                    TOTAL BOOKINGS FOR THE DAY
                </h6>

                <!-- Heading -->
                <span class="h2 mb-0">
                       <c:out value="${totalbookings}" />
                    </span>

                <!-- Badge -->

            </div>

        </div>

    </div>

    <div class="container mt-3 p-3 bg-body rounded shadow-sm">
        <c:choose>
            <c:when test="${not empty dailybookings}">
                <table id="" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Driver ID</th>
                        <th>Customer ID</th>
                        <th>Start</th>
                        <th>Destinatiom</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Fee</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${dailybookings}" var="j">
                        <tr>
                            <td><c:out value="${j.id}"/></td>
                            <td><c:out value="${j.driver_id}"/></td>
                            <td><c:out value="${j.customer_id}"/></td>
                            <td><c:out value="${j.start}"/></td>
                            <td><c:out value="${j.end}"/></td>
                            <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                            <td>
                                <c:set var = "status" value = "${j.status}" />
                                <c:if test="${status == '0'}">
                                    <span class="badge bg-dark">Pending</span>
                                </c:if>
                                <c:if test="${status == '1'}">
                                    <span class="badge bg-success">Approved</span>
                                </c:if>
                                <c:if test="${status == '2'}">
                                    <span class="badge bg-info">Paid</span>
                                </c:if>
                                <c:if test="${status == '3'}">
                                    <span class="badge bg-danger">Rejected</span>
                                </c:if>
                            </td>
                            <td><fmt:formatNumber value = "${j.fee}"  /></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </c:when>

            <c:when test="${empty dailybookings}">
                <div class="alert alert-warning d-flex alight-items-center" role="alert">
                    <div>No bookings to show!</div>
                </div>
            </c:when>




        </c:choose>



    </div>














</main>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>