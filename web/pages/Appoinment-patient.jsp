<%-- 
    Document   : Appoinment-patient
    Created on : Mar 15, 2023, 7:00:05 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital</title>
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
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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
    </head>
    <body>
        <section class="container-fluid bg-light">
            <%@include file="nav-patient.html" %>
            <div class="collapse position-absolute" id="collapseExample">
                <div class="card card-body bg-light bg-opacity-75">
                    <div class="container py-5 h-100">
                        <div class="p-4 d-flex justify-content-around">
                            <form action="Controller?page=addAppoinment" method="post" role="form" class="needs-validation">
                                <div class="row">
                                    <div class="col-md-6 form-group mt-3">
                                        <input type="date" name="date" class="form-control datepicker" id="date" required/>
                                        <div class="validate"></div>
                                    </div>
                                    <div class="col-md-6 form-group mt-3">
                                        <input type="time" name="time" class="form-control datepicker" id="time" required/>
                                        <div class="validate"></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 form-group mt-3">
                                        <select name="doctor" id="doctor" class="form-select">
                                            <option value="">Select Doctor</option>
                                            <c:forEach var="Doctor" items="${doctorList}">
                                                <option value="${Doctor.user.id}"> - ${Doctor.user.name}</option>
                                            </c:forEach>    
                                        </select>
                                        <div class="validate"></div>
                                    </div>
                                </div>

                                <div class="form-group mt-3">
                                    <textarea class="form-control" name="message" rows="5" placeholder="Message (Optional)"></textarea>
                                    <div class="validate"></div>
                                </div>
                                <div class="text-center mt-4 ">
                                    <button type="submit" class="btn bg-info p-2">Make an Appointment</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Appoinment</h5>
                            <small>Patient > Appoinment</small>
                        </div>
                    </div>
                    <div class="col-auto page-action">
                        <button  class="btn btn-light bg-info"
                                 type="button"
                                 data-toggle="collapse"
                                 data-target="#collapseExample"
                                 aria-expanded="false"
                                 aria-controls="collapseExample">
                            Add Appoinment
                        </button>
                    </div>
                </div>
                <table class="table">
                    <thead>
                    <td>Image</td>
                    <td>Name</td>
                    <td>Gender</td>
                    <td>Age</td>
                    <td>Phone</td>
                    <td>Address</td>
                    <td>Date</td>
                    <td>Time</td>
                    <td>Action</td>
                    </thead>
                    <c:forEach var="appoinment" items="${appoinmentList}">
                        <tr>
                            <td>${appoinment.user.id}</td>
                            <td>${appoinment.user.name}</td>
                            <td>${appoinment.user.gender}</td>
                            <td>${appoinment.user.age}</td>
                            <td>${appoinment.user.phone}</td>
                            <td>${appoinment.user.address}</td>
                            <td>${appoinment.sechedule.date}</td>
                            <td>${appoinment.sechedule.visitTime} - ${appoinment.sechedule.endTime}</td>
                            <td>
                                <button type="button" class="btn btn-primary" onclick="DeleteAction(${appoinment.sechedule.id})" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <form action="Controller?page=deleteAppoinment" method="post">
                                <input type="hidden"  name="delete" id="myInput" />
                                <button type="submit" class="btn btn-danger">Confirm</button>
                            </form> 
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
    <script>
        function DeleteAction(a) {
            const deleteForm = document.getElementById("myInput");
            deleteForm.value = a;
        }
    </script>
</body>
</html>
