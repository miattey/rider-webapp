<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rider - Online Minicab Booking</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-weight: 300;
            font-size: 0.9rem;
        }

        .form-signin {
            width: 100%;
            max-width: 600px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

    </style>

</head>
<body class="text-center">

<main class="form-signin">
    <form action="register" method="POST">
        <div class="bg-dark align-items-center p-3 my-3 text-white shadow-sm">
            <div>
                <img class="" src="img/LOGO2.png" alt="" width="" height="60">
            </div>

            <div>
                <div class="text-center pt-3 lh-1">
                    <h1 class="h4 mb-0 text-black lh-1">RIDER</h1>
                    <small>Online Minicab Booking System</small>
                </div>
            </div>
        </div>

        <div class="card p-3">
            <h1 class="h4 mb-2 fw-normal">Create a New Account</h1>
            <br>

            <c:choose>
                <c:when test="${not empty errMessageRegister}">
                    <div class='alert alert-danger'><c:out value="${errMessageRegister}" /></div>
                </c:when>
            </c:choose>

            <div class="form-floating m-1">
                <input type="text" name="username" class="form-control" id ="floatingInput" placeholder="Username" autofocus="autofocus" required>
                <label for="floatingInput">Username</label>
            </div>

            <div class="form-floating m-1">
                <input type="password" name="password" class="form-control" id ="floatingInput2"  placeholder="password" autofocus="autofocus" required>
                <label for="floatingInput2">Pasword</label>
            </div>

            <div class="form-floating m-1">
                <input type="password" name="password2" class="form-control" id ="floatingInput3"  placeholder="Confirm Password" autofocus="autofocus" required>
                <label for="floatingInput3">Confirm Password</label>
            </div>

            <div class="form-floating m-1">
                <input type="text" name="firstName" class="form-control" id ="floatingInput4"  placeholder="First Name" autofocus="autofocus" required>
                <label for="floatingInput4">First Name</label>
            </div>

            <div class="form-floating m-1">
                <input type="text" name="lastName" class="form-control" id ="floatingInput5"  placeholder="Last Name" autofocus="autofocus" required>
                <label for="floatingInput5">Last Name</label>
            </div>

            <div class="form-floating m-1">
                <input type="text" name="address" class="form-control" id ="floatingInput6"  placeholder="Address" autofocus="autofocus" required>
                <label for="floatingInput6">Address</label>
            </div>



            <button class="w-100 btn btn-lg btn-danger mt-2"  type="submit">Register</button>

            <p class="mt-3 mb-3 text-muted">Already a member? <a href="login" class="link-dark">Login</a></p>


        </div>
    </form>
</main>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>