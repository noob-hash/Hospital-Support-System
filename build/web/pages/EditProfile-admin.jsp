<%-- 
    Document   : EditProfile
    Created on : Mar 30, 2023, 6:45:39 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon1.png" rel="icon">
        <link href="assets/img/apple-touch-icon_1.png" rel="apple-touch-icon">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <style>
        * {
            margin: 0;
            padding: 0;
            font-family: "Montserrat", sans-serif;
        }
        a{
            text-decoration: none;
            color: #000;
        }

        header {
            position: fixed;
            z-index: 2;
            left: 0%;
            top: 0%;
            width: 100%;
            height: 10%;
        }
        nav {
            position: fixed;
            z-index: 2;
            left: 0%;
            top: 10%;
            width: 15%;
            height: 90%;
        }
        main {
            position: absolute;
            left: 15%;
            top: 10%;
            width: 85%;
            height: 90%;
            overflow-x: hidden
        }
    </style>
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
        <section id="page" class="cotainer-fluid">
            <%@include file="nav-admin.jsp" %>
            <div class="p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Profile</h5>
                            <small>Admin > Profile > Edit Profile</small>
                        </div>
                    </div>
                </div>
                <div class="card">
<div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

 

                <img src="./Pages/assets/img/profile1.jpg" alt="Profile" class="rounded-circle">
<h2>Admin Account</h2>
<h3>Overview</h3>

</div>
<div class="card-body pt-3">
<!-- Bordered Tabs -->
<ul class="nav nav-tabs nav-tabs-bordered">

 

                <li class="nav-item">
<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Overview</button>
</li>

 

                  <li class="nav-item">
<button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">Change Password</button>
</li>

 

              </ul>
<div class="tab-content pt-2">

 

                <div class="tab-pane fade show active profile-overview" id="profile-overview">


 

                  <h5 class="card-title">Profile Details</h5>

<div class="row">
<div class="col-lg-3 col-md-4 label ">Full Name</div>
<div class="col-lg-9 col-md-8"> ${admindetails.fullname}</div>
</div>

 

                  <div class="row">
<div class="col-lg-3 col-md-4 label">Address</div>
<div class="col-lg-9 col-md-8">${admindetails.address}</div>
</div>

 

                  <div class="row">
<div class="col-lg-3 col-md-4 label">Phone</div>
<div class="col-lg-9 col-md-8">${admindetails.phone}</div>
</div>

 

                  <div class="row">
<div class="col-lg-3 col-md-4 label">Email</div>
<div class="col-lg-9 col-md-8">${admindetails.email}</div>
</div>

 

                </div>

 

               


 

                <div class="tab-pane fade pt-3" id="profile-change-password">
<!-- Change Password Form -->
<form>

 

                    <div class="row mb-3">
<label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
<div class="col-md-8 col-lg-9">
<input name="password" type="password" class="form-control" id="currentPassword">
</div>
</div>

 

                    <div class="row mb-3">
<label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
<div class="col-md-8 col-lg-9">
<input name="newpassword" type="password" class="form-control" id="newPassword">
</div>
</div>

 

                    <div class="row mb-3">
<label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
<div class="col-md-8 col-lg-9">
<input name="renewpassword" type="password" class="form-control" id="renewPassword">
</div>
</div>

 

                    <div class="text-center">
<button type="submit" class="btn btn-primary">Change Password</button>
</div>
</form><!-- End Change Password Form -->

 

                </div>

 

              </div><!-- End Bordered Tabs -->

 

            </div>
</div>
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
                                            <h3>Personal Info</h3>

                                            <form method="post" action="Controller?page=updateUser" class="px-md-2">
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label for="Name">Full Name</label>
                                                        <input required
                                                               type="text"
                                                               class="form-control"
                                                               id="Name"
                                                               name="Name"
                                                               value="${User.name}"
                                                               aria-describedby="emailHelp"
                                                               placeholder="Enter fullname"
                                                               />
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
                                                                   value="${User.email}"
                                                                   aria-describedby="emailHelp"
                                                                   placeholder="Enter email"
                                                                   />
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <label for="Phone">Contact</label>
                                                            <input required
                                                                   type="text"
                                                                   inputmode="numeric"
                                                                   pattern="[0-9]+"
                                                                   class="form-control"
                                                                   id="Phone"
                                                                   name="Phone"
                                                                   value="${User.phone}"
                                                                   aria-describedby="emailHelp"
                                                                   placeholder="Enter phone"
                                                                   />
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
                                                        <input required type="date"  name="DOB" value="${User.dob}" class="form-control" id="dob" />
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <label for="address">Address</label>
                                                        <input required
                                                               type="text"
                                                               class="form-control"
                                                               name="Address"
                                                               value="${User.address}"
                                                               id="address"
                                                               placeholder="Enter address"
                                                               />
                                                    </div>
                                                </div>

                                                <div class="row ">
                                                    <button
                                                        type="submit"
                                                        class="btn btn-success btn-lg mt-4 mb-1 center"
                                                        >
                                                        Submit Edit
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <form action="Controller?page=updatePassword" method="post">
                        <div class="row">
                            <div class="row">

                                <div class="col-lg-12">
                                    <label for="oldPwd">Current Password</label>
                                    <input required
                                           type="password"
                                           class="form-control"
                                           name="CurrentPassword"
                                           id="oldPwd"
                                           placeholder="Current Password"
                                           />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <label for="Password">New Password</label>
                                <input required
                                       type="password"
                                       class="form-control"
                                       name="NewPassword"
                                       id="Password"
                                       placeholder="Enter Password"
                                       />
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
                            </div>
                        </div>
                        <input type="Submit" value="Submit"/>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
