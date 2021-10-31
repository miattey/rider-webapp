<%@ page import="model.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Manage Drivers</title>
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
                                    <a class="nav-link dropdown-toggle active" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Manage Users</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="managedrivers">Manage Drivers</a></li>
                                        <li><a class="dropdown-item" href="managecustomers?page=1">List All Customers</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Generate Reports</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Recent Bookings</a></li>
                                        <li><a class="dropdown-item" href="#">Another action</a></li>
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


            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    <a  role="button" class="btn btn-secondary float-end" href="addnewdriver">Add New Driver </a>
                    </div>

                </div>
             </div>


            <div class="container mt-3 p-3 bg-body rounded shadow-sm">
                <table id="" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Driver ID</th>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Vehicle Registration No.</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${all_drivers}" var="driver">
                            <tr>
                                <td><c:out value="${driver.ID}"/></td>
                                <td><c:out value="${driver.username}"/></td>
                                <td><c:out value="${driver.firstName}"/></td>
                                <td><c:out value="${driver.lastName}"/></td>
                                <td><span class="badge rounded-pill bg-warning text-dark"><c:out value="${driver.reg}"/></span></td>

                                <td>
                                    <form class="form-inline" method="POST" action="managedrivers" >
                                        <button type="submit" class="btn-sm btn-danger mb-2" name="delete_id" value="<c:out value="${driver.ID}"/>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                        </svg></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>