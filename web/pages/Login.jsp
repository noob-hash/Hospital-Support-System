<%-- 
    Document   : Login
    Created on : Mar 8, 2023, 10:42:24 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital-Login</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon1.png" rel="icon">
        <link href="assets/img/apple-touch-icon_1.png" rel="apple-touch-icon">
    </head>
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap");
    </style>
    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
        integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer"
        />
</head>
<body>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-light text-dark border-1">
                        <div class="card-body p-4">

                            <form action="Controller?page=login" method="post" class="mb-md-2 mt-md-4 pb-2">

                                <h2 class="fw-bold mb-2  text-center text-uppercase">Login</h2>
                                <p class="text-white-50 mb-2">Please enter your login and password!</p>

                                <div class="form-outline form-white">
                                    <label class="form-label " for="username">Username/Email</label>
                                    <input type="text" id="username" name="username" class="form-control form-control-lg" />
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="password">Password</label>
                                    <input type="password" id="password" name="password" class="form-control form-control-lg" />
                                </div>
                                <div>
                                    <input type="checkbox" name="Remember" id="Remenber" />
                                    <label for="Remenber">Remember me</label>
                                </div>
                                <p class="small mb-2 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
                                <div class=" text-center">
                                    <button class="btn btn-primary btn-lg px-5" type="submit">Login</button>
                                </div>
                                <div class="text-center mt-2">
                                    OR
                                </div>
                                <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                    <a href="#!"><i class="fab fa-facebook-f"></i></a>
                                    <a href="#!"><i class="fab fa-twitter mx-4 px-2"></i></a>
                                    <a href="#!"><i class="fab fa-google"></i></a>
                                </div>
                                <div class="text-center mt-2">
                                    <p class="mb-0">Don't have an account? <a href="Controller?page=registerPage" class=" fw-bold">Sign Up</a>
                                    </p>
                                </div>
                        </div>



                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>