<%-- 
    Document   : Appoinment-patient
    Created on : Mar 15, 2023, 7:00:05 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>
        <section class="container-fluid bg-light">
            <%@include file="nav-patient.html" %>
<!--            <div class="p-4 d-flex justify-content-around">
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
                        <div class="col-md-6 form-group mt-3">
                            <select name="department" id="department" class="form-select">
                                <option value="">Select Department</option>
                                <option value="Department 1">Department 1</option>
                                <option value="Department 2">Department 2</option>
                                <option value="Department 3">Department 3</option>
                            </select>
                            <div class="validate"></div>
                        </div>
                        <div class="col-md-6 form-group mt-3">
                            <select name="doctor" id="doctor" class="form-select">
                                <option value="">Select Doctor</option>
                                <option value="Doctor 1">Doctor 1</option>
                                <option value="Doctor 2">Doctor 2</option>
                                <option value="Doctor 3">Doctor 3</option>
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
            </div>-->
<div>
    <table>
        <c:forEach var="appoinment" items="${appoinmentList}">
                        <tr>
                            <td>${appoinment.user.id }</td>
                            <td>${appoinment.user.name}</td>
                            <td>${appoinment.user.phone}</td>
                            <td>${appoinmentuser.role}</td>
                            <td>${appoinment.user.address}</td>
                            <td>${appoinment.user.gender}</td>
                            <td>${appoinment.user.age}</td>
                            <td><a href="UserServlet?id="${user.id}>Delete</a></td>
                        </tr>
                    </c:forEach>
    </table>
</div>
        </section>
    </body>
</html>
