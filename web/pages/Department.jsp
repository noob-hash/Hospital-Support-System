<%-- 
    Document   : Doctor-list
    Created on : Mar 5, 2023, 9:16:37 AM
    Author     : Subin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department</title>
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
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: "Montserrat", sans-serif;
        }
        .hidden{
            display: none;
        }
        .page-action{
            display: flex;
            justify-content: center;
            position: fixed!important;
            right: 1.42857rem;
            z-index: 997;
        }
        a{
            text-decoration: none;
            color: #000;
        }

        .col-md-4{
            width:32.333333%;
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
    <script
        src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"
    ></script>
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
    <section class="container-fluid">
        <%@include  file="nav-admin.jsp"%>
        <div class="collapse position-absolute top-0" id="collapseExample">
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

                                    <form action="Controller?page=addDepartment" method="POST" class="px-md-2">
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label for="Name">Name</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    name="Name"
                                                    id="Name"
                                                    placeholder="Enter department name"
                                                    />
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="Contact">Contact</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="Contact"
                                                    name="Contact"
                                                    placeholder="Enter contact"
                                                    />
                                            </div>
                                        </div>

                                        <div>
                                            <label for="Descciption">Descciption</label><br>
                                            <textarea name="Descciption" id="Descciption" class="form-control"
                                                      cols="65" rows="5" placeholder="Enter Descciption"
                                                      style="resize: none;">                    
                                            </textarea>
                                        </div>
                                        <button
                                            type="submit"
                                            class="btn btn-success btn-lg mt-4 mb-1"
                                            >
                                            Submit
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="p-4">
            <div class="row">
                <div class="col">
                    <div class="">
                        <h5>Department</h5>
                        <small>Admin > Department List</small>
                    </div>
                </div>
                <div class="col-auto page-action">
                    <button  class="btn btn-light bg-info"
                             type="button"
                             data-toggle="collapse"
                             data-target="#collapseExample"
                             aria-expanded="false"
                             aria-controls="collapseExample">
                        Add Department
                    </button>
                </div>
            </div>
            <div class="row p-2 gap-2">
                <c:forEach var="department" items="${departmentList}">
                    <div class="col-sm-12 col-md-4 bg-white rounded-2 p-2">
                        <div class="contact ">
                            <div class="img-box">
                                <img width="400" src="content/doctor-400-1.jpg" height="400" alt="avatar">
                            </div><div class="info-box">
                                <h4 class="name">${department.name}</h4>
                                <small class="contact">${department.contact}</small>
                                <p class="role">${department.detail}</p>

                                <div class="button-box">
                                    <button type="button" class="btn btn-primary">
                                        <span>More</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>
        </div>
    </section>

</body>
</html>
