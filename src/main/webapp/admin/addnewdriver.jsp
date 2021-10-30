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
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Add Driver</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Manage Users</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Reports</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Recent Bookings</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Separated link</a></li>
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



            <c:choose>
                <c:when test="${not empty errormessage}">
                    <div class='alert alert-danger'><c:out value="${errormessage}" /></div>
                </c:when>
            </c:choose>




            <div class="container p-3 bg-body rounded shadow-sm">
                <div class="d-flex text-muted pt-3" >
                    <div class="container">

                        <form action="addnewdriver" method="POST">
                        <div>
                            <div class="bg-light p-3 border-bottom">
                            <h1 class="h4 mb-2 fw-normal">Add New Driver</h1>
                            <p>Please fill out the driver details</p>
                            </div>
                            <br>

                            <c:choose>
                                <c:when test="${not empty errMessage}">
                                    <div class='alert alert-danger'><c:out value="${errMessage}" /></div>
                                </c:when>
                            </c:choose>

                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label for="inputUsername" class="form-label">Username</label>
                                        <div class="input-group">
                                            <div class="input-group-text">@</div>
                                            <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Username" required>
                                        </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="inputPassword" class="form-label">Password</label>
                                    <input type="password" name="password" class="form-control" id="inputPassword" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="inputFirst" class="form-label">First Name</label>
                                    <input type="text" name="firstname" class="form-control" id="inputFirst" placeholder="" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="inputLast" class="form-label">Last Name</label>
                                    <input type="text"  name="lastname" class="form-control" id="inputLast" placeholder="" required>
                                </div>

                                <div class="col-12">
                                    <label for="inputVehicleReg" class="form-label">Vehicle Registration Number</label>
                                    <input type="text" name="vehicleregno" class="form-control" id="inputVehicleReg" placeholder="AA0-0000" required>
                                </div>

                                <div class="col-12 p-3">
                                    <button type="submit" name="add_new" value="1" class="btn btn-danger float-end">Add new Driver</button>
                                </div>
                            </div>








                        </div>
                    </form>

                    </div>
                </div>



            </div>

        </div>

    </div>










</main>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>