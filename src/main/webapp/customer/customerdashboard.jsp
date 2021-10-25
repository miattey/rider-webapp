<%@ page import="model.pojo.User" %>
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
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?language=en&key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=places"></script>
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

    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>

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




    <div class="tab-content" id="pills-tabContent">

        <!-- Customer bookings details tab goes here -->
        <div class="bg-body rounded shadow-sm tab-pane fade show active" id="pills-mybookings" role="tabpanel" aria-labelledby="pills-mybookings-tab">




            <div class="d-flex text-muted pt-3 border-bottom" >
                <div class="container">
                    <c:choose>
                        <c:when test="${not empty succCust}">
                            <div class='alert alert-success'><c:out value="${succCust}"/></div>
                        </c:when>
                        <c:when test="${not empty errCust}">
                            <div class='alert alert-danger'><c:out value="${errCust}"/></div>
                        </c:when>
                    </c:choose>


                        <c:choose>
                            <c:when test="${not empty mybookings}" >

                                <table id="todays_journeys" class="table table-striped table-bordered" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Start Address</th>
                                        <th>Destination</th>
                                        <th>Assigned Driver</th>
                                        <th>Fee (MVR)</th>
                                        <th>Status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${mybookings}" var="j">
                                        <tr>
                                            <td><fmt:formatDate type = "date" value = "${j.date}" /></td>
                                            <td><fmt:formatDate type = "time" pattern = "HH:mm" value = "${j.time}" /></td>
                                            <td><c:out value="${j.start}"/></td>
                                            <td><c:out value="${j.end}"/></td>

                                            <c:set var = "name" value = "${j.getDriver().getReg()}" />
                                            <c:set var = "firstname" value = "${j.getDriver().getFirstName()}" />
                                            <c:set var = "lastname" value = "${j.getDriver().getLastName()}" />
                                            <c:set var = "status" value = "${j.status}" />

                                            <c:if test="${empty name}">
                                            <td>
                                                <span class="badge bg-danger">-</span>
                                            </td>
                                            </c:if>
                                            <c:if test="${not empty name}">
                                            <td>
                                                <span class="badge rounded-pill bg-warning text-dark"><c:out value="${firstname}"/> <c:out value="${lastname}"/> - <c:out value="${name}"/></span>
                                            </td>
                                            </c:if>
                                            <td><fmt:formatNumber value = "${j.fee}"  /></td>


                                                <td>
                                                    <c:if test="${status == '0'}">
                                                    <span class="badge bg-danger">Pending</span>
                                                    </c:if>
                                                    <c:if test="${status == '1'}">
                                                        <span class="badge bg-success">Approved</span>
                                                    </c:if>
                                                    <c:if test="${status == '2'}">
                                                        <span class="badge bg-primary">Paid</span>
                                                    </c:if>
                                                </td>



                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <hr>
                            </c:when>
                            <c:when test="${empty mybookings}" >
                                <div class="alert alert-warning d-flex align-items-center" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    <div>
                                        No Bookings to show!
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>

                </div>
            </div>






        </div>
        <!-- ends here -->

        <!-- Make booking tab goes here -->
        <div class="tab-pane fade" id="pills-makebookings" role="tabpanel" aria-labelledby="pills-makebookings-tab">

            <div class="container">
                <div class="row">
                    <div class="col-sm-8 bg-body rounded shadow-sm">
                        <h6 class="bg-light border-bottom p-2 mb-3">Booking details</h6>

                        <form method="POST" action="makebooking">
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
                                    <p><button type="button" onclick="calculateDistances();">Calculate
                                        distances</button></p>
                                </div>
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
                                        <input type="submit" id="sub_button" value="Create Booking" class="btn btn-primary" disabled/>

                                    </div>
                                </div>
                            </div>

                    </div>


                    <div class="col-sm-4">
                        <div class="row justify-content-md-center">

                            <div class="order-md-last">
                                <ul class="list-group mb-3">
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            Booking Summary
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">Distance</h6>
                                            <small class="text-muted"><div class="input-group mb-3">
                                                <label ><label id="distance_shower" ></label> Meters</label>
                                                <input id="distanceqw" type="hidden" name="distanceqw" value=""/>
                                            </div></small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">Journey Time</h6>
                                            <small class="text-muted"><div class="input-group mb-3">
                                                <label id="approx_time"></label>
                                                <input type="hidden" name="" value=""/>
                                            </div></small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between">
                                        <span>Total (MVR)</span>

                                        <div id="outputDiv"></div>

                                        <strong><div class="input-group mb-3">
                                            </label><label id="fare_amount"></label>
                                            <input id="fare_amounthidden" type="hidden" name="fare_amounthidden" value=""/>
                                        </div></strong>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                    </form>
                </div>

            </div>
            <!-- make booking tab ends here -->



        </div>






    </div>




</main>



<script>
    var map;
    var geocoder;
    var bounds = new google.maps.LatLngBounds();
    var markersArray = [];

    //var origin1 = new google.maps.LatLng(55.930, -3.118);
    var origin;
    var destination;
    //var destinationB = new google.maps.LatLng(50.087, 14.421);



    function initialize() {
        geocoder = new google.maps.Geocoder();


    }

    function calculateDistances() {
        origin = document.getElementById('start_address').value;
        destination = document.getElementById('destination_address').value;
        var service = new google.maps.DistanceMatrixService();
        service.getDistanceMatrix(
            {
                origins: [origin],
                destinations: [destination],
                travelMode: google.maps.TravelMode.DRIVING,
                unitSystem: google.maps.UnitSystem.METRIC,
                avoidHighways: false,
                avoidTolls: false
            }, calcDistance);

    }

    function calcDistance(response, status) {
        if (status != google.maps.DistanceMatrixStatus.OK) {
            alert('Error was: ' + status);
        } else {
            var origins = response.originAddresses;
            var destinations = response.destinationAddresses;
            deleteOverlays();



            for (var i = 0; i < origins.length; i++) {
                var results = response.rows[i].elements;

                for (var j = 0; j < results.length; j++) {

                    //outputDiv.innerHTML += origins[i] + ' to ' + destinations[j]
                    //  + ': ' + results[j].distance.text + ' in '
                    //+ results[j].duration.text + '<br>';

                    distance_shower.innerHTML = results[j].distance.value/1000;
                    approx_time.innerHTML = results[j].duration.text;
                    fare_amount.innerHTML = Math.round((results[j].distance.value/1000)*50);

                    document.getElementById("distanceqw").value = results[j].distance.value/1000;
                    document.getElementById("fare_amounthidden").value = Math.round((results[j].distance.value/1000)*50);


                    document.getElementById("sub_button").disabled = false;



                }
            }
        }
    }



    function deleteOverlays() {
        for (var i = 0; i < markersArray.length; i++) {
            markersArray[i].setMap(null);
        }
        markersArray = [];
    }

    google.maps.event.addDomListener(window, 'load', initialize);

</script>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>