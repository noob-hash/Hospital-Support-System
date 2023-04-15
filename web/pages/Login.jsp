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

        <!-- Used for login through google api -->
        <meta name="google-signin-client_id" content="668452761339-j6gv3e5hlo3emjatase31gvgq2s39c3k.apps.googleusercontent.com">

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
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</head>
<body>
    <section class="vh-100 gradient-custom">

        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-light text-dark border-1">
                        <div class="card-body p-4">
                            <form action="Controller?page=login" method="post" class="mb-md-2 mt-md-4 pb-2 needs-validation" novalidate>

                                <h2 class="fw-bold mb-2  text-center text-uppercase">Login</h2>
                                <p class="text-white-50 mb-2">Please enter your login and password!</p>

                                <div class="form-outline form-white">
                                    <label class="form-label " for="username">Username</label>
                                    <input  type="text" id="username" name="username" class="form-control form-control-lg" pattern="[0-9]{10}" required/>
                                    <div class="valid-feedback">Valid Username</div>
                                    <div class="invalid-feedback">
                                        Username must be your phone number
                                    </div>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="password">Password</label>
                                    <input  type="password" id="password" name="password" class="form-control form-control-lg" minlength="8" required/>
                                    <div class="valid-feedback">Valid password</div>
                                    <div class="invalid-feedback">
                                        Password field cannot less than 8 character
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="checkbox" name="Remember" id="Remenber" />
                                        <label for="Remenber">Remember me</label>
                                    </div>
                                    <div class="col-6 text-right" style="text-align:right">
                                        <a href="${pageContext.request.contextPath}/Controller?page=forget">Forget Password</a>
                                    </div>
                                </div>
                                <p class="small mb-2 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
                                <div class=" text-center">
                                    <button class="btn btn-primary btn-lg px-5" type="submit">Login</button>
                                </div>
                                <div class="text-center mt-2">
                                    OR
                                </div>
                                <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                    <script src="https://accounts.google.com/gsi/client" async defer></script>
                                    <div id="g_id_onload"
                                         data-client_id="668452761339-j6gv3e5hlo3emjatase31gvgq2s39c3k.apps.googleusercontent.com"
                                         data-login_uri="https://localhost:8080/HSS/Controller?page=login"
                                         data-auto_prompt="false">
                                    </div>
                                    <div class="g_id_signin"
                                         data-type="standard"
                                         data-size="large"
                                         data-theme="outline"
                                         data-text="sign_in_with"
                                         data-shape="rectangular"
                                         data-logo_alignment="left">
                                    </div>
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
<script>
    (function () {
        'use strict'

// Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

// Loop over them and prevent submission
        Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
    })()
</script>
</body>
</html>