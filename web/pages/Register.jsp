<%-- 
    Document   : Register
    Created on : Mar 7, 2023, 4:41:12 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital-Register</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon1.png" rel="icon">
        <link href="assets/img/apple-touch-icon_1.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
        <div class="center top-0">
            <div class="card card-body bg-light bg-opacity-75">
                <div class="container py-5 h-100">
                    <div
                        class="row d-flex justify-content-center align-items-center h-100"
                        >
                        <div class="col-lg-8 col-xl-6">
                            <div class="card rounded-3">
                                <img
                                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
                                    class="w-100"
                                    style="
                                    border-top-left-radius: 0.3rem;
                                    border-top-right-radius: 0.3rem;
                                    "
                                    alt="Sample photo"
                                    />
                                <div class="card-body p-md-3">
                                    <h3>Registration Info</h3>

                                    <form method="post" action="Controller?page=addUser" class="px-md-2 needs-validation" novalidate>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label for="Name">Full Name</label>
                                                <input required
                                                       type="text"
                                                       class="form-control"
                                                       id="Name"
                                                       name="Name"
                                                       aria-describedby="Full name"
                                                       placeholder="Enter fullname"
                                                       />
                                                <div class="invalid-feedback">
                                                    Please enter your name
                                                </div>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="Email1">Gender</label><br />
                                                <div class="mt-2">
                                                    <input required
                                                           type="radio"
                                                           name="Gender"
                                                           id="Male"
                                                           value="M"
                                                           />
                                                    <label for="Male">Male</label>
                                                    <input required
                                                           type="radio"
                                                           name="Gender"
                                                           id="Female"
                                                           value="F"
                                                           />
                                                    <label for="Female">Female</label>
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please select your gender
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label for="Email1">Email address</label>
                                                    <input required
                                                           type="email"
                                                           class="form-control"
                                                           id="Email"
                                                           name="Email"
                                                           aria-describedby="emailHelp"
                                                           placeholder="Enter email"
                                                           />
                                                    <div class="invalid-feedback">
                                                    Please enter your email
                                                </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label for="Phone">Contact</label>
                                                    <input required
                                                           type="text"
                                                           inputmode="numeric"
                                                           pattern="[0-9]{10}"
                                                           class="form-control"
                                                           id="Phone"
                                                           name="Phone"
                                                           aria-describedby="emailHelp"
                                                           placeholder="Enter phone"
                                                           />
                                                    <div class="invalid-feedback">
                                                    Please enter your contact
                                                </div>
                                                </div>
                                            </div>
                                            <small id="emailHelp" class="form-text text-muted"
                                                   >We'll never share your email and contact with anyone
                                                else.</small
                                            >
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <label for="dob">Birth Date</label>
                                                <input required type="date" name="DOB" class="form-control" id="dob" />
                                            </div>
                                            <div class="col-lg-6">
                                                <label for="address">Address</label>
                                                <input required
                                                       type="text"
                                                       class="form-control"
                                                       name="Address"
                                                       id="address"
                                                       placeholder="Enter address"
                                                       />
                                                <div class="invalid-feedback">
                                                    Please enter your DOB
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <label for="Password">Password</label>
                                                <input required
                                                       type="password"
                                                       class="form-control"
                                                       name="Password"
                                                       id="Password"
                                                       placeholder="Enter Password"
                                                       pattern="(?=.*?[~`!@#$%^&amp;*()_=+\[\]{};:&apos;.,&quot;\\|\/?&gt;&lt;-])(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                       />
                                                <div class="invalid-feedback">
                                                    At least 8 character with uppercase, lowercase and special character
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <label for="confirmPwd">Confirm Password</label>
                                                <input required
                                                       type="password"
                                                       class="form-control"
                                                       name="ConfirmPassword"
                                                       id="confirmPwd"
                                                       placeholder="Confirm Password"
                                                       />
                                                <div class="invalid-feedback">
                                                    Please repeat your password
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <button
                                                type="submit"
                                                class="btn btn-success btn-lg mt-4 mb-1 center"
                                                >
                                                Create Account
                                            </button>
                                        </div>
                                        <div>
                                            <p class="mb-0">Already have an account? <a href="Controller?page=loginPage" class=" fw-bold">Login</a>
                                            </p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
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
</html>
