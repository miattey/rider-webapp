<%@ page import="model.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rider - Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=places"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>

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
                    <ul class="nav nav-pills" id="pills-tab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="pills-mybookings-tab" data-bs-toggle="pill" data-bs-target="#pills-mybookings" type="button" role="tab" aria-controls="pills-mybookings" aria-selected="true">My Bookings</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-makebookings-tab" data-bs-toggle="pill" data-bs-target="#pills-makebookings" type="button" role="tab" aria-controls="pills-makebookings" aria-selected="false">Make Booking</button>
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

<!-- header ends here -->


    <div class="container p-3 bg-body rounded shadow-sm">

        <div class="tab-content" id="pills-tabContent">

            <!-- Customer bookings details tab goes here -->
            <div class="tab-pane fade show active" id="pills-mybookings" role="tabpanel" aria-labelledby="pills-mybookings-tab">
                <div>
                    <h2>Customer Dashbaord</h2>
                    <h3>Functionalities for customer dashboard goes here</h3>
                </div>
            </div>
            <!-- ends here -->

            <!-- Make booking tab goes here -->
            <div class="tab-pane fade" id="pills-makebookings" role="tabpanel" aria-labelledby="pills-makebookings-tab">
                <h6 class="bg-light border-bottom p-2 mb-3">Booking details</h6>

                        <form method="POST" action="<%=request.getContextPath()%>/CreateBooking">
                            <div class="row">

                                <div class="col">
                                    <label>Starting address:</label>
                                    <div class="input-group mb-3">
                                        <input type="text" placeholder="Starting address" id="start_address" class="form-control" name="start_address" autofocus="autofocus" required/>
                                    </div>
                                    <script>
                                        function init() {
                                            const maldivesBounds = {
                                                north:4.248855266395262,
                                                south: 4.162057293027988,
                                                west: 73.44143199243884,
                                                east: 73.61455248799393,
                                            };

                                            var options = {
                                                bounds: maldivesBounds,

                                                componentRestrictions: {country: 'MV'},
                                                strictBounds: true,
                                            };

                                            var input = document.getElementById('start_address');
                                            var autocomplete = new google.maps.places.Autocomplete(input,options);
                                        }
                                        google.maps.event.addDomListener(window, 'load', init);
                                    </script>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label>Destination address:</label>
                                    <div class="input-group mb-3">
                                        <input type="text" placeholder="Destination address" id="destination_address" class="form-control" name="destination_address" required/>
                                    </div>
                                </div>
                                <script>
                                    function init2() {
                                        const maldivesBounds = {
                                            north:4.248855266395262,
                                            south: 4.162057293027988,
                                            west: 73.44143199243884,
                                            east: 73.61455248799393,
                                        };

                                        var options = {
                                            bounds: maldivesBounds,

                                            componentRestrictions: {country: 'MV'},
                                            strictBounds: true,
                                        };

                                        var input = document.getElementById('destination_address');
                                        var autocomplete = new google.maps.places.Autocomplete(input, options);
                                    }
                                    google.maps.event.addDomListener(window, 'load', init2);
                                </script>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label>Date of booking:</label>
                                    <div class="input-group mb-3">
                                        <input type="date" placeholder="Date of booking" class="form-control" name="date" required/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label>Time of booking:</label>
                                    <div class="input-group mb-3">
                                        <input type="time" placeholder="Time of booking" class="form-control" name="time" required/>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <input type="hidden" value="new" name="stage_booking"/>
                                        <input type="submit" value="Create Booking" class="btn btn-primary"/>
                                    </div>
                                </div>
                            </div>
                        </form>
            </div>
            <!-- make booking tab ends here -->



        </div>

    </div>









</main>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>