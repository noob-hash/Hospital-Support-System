<%-- 
    Document   : Dashboard-admin
    Created on : Mar 1, 2023, 6:32:01 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Dashboard</title>
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
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>


    <body>
        <section id="page" class="cotainer-fluid">
            <%@include file="nav-admin.jsp" %>
            <div class="p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Dashboard</h5>
                            <small>Admin > Dashboard</small>
                        </div>
                    </div>
                </div>
                <div class="p-4 d-flex justify-content-around row">
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4">
                        <div></div>
                        <div>
                            <div><b>Appoinments</b></div>
                            <div><b>213</b></div>
                        </div>
                    </div>
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4">
                        <div></div>
                        <div>
                            <div><b>New Patients</b></div>
                            <div><b>213</b></div>
                        </div>
                    </div>
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4">
                        <div></div>
                        <div>
                            <div><b>Total Patients</b></div>
                            <div><b>213</b></div>
                        </div>
                    </div>
                </div>
                <!-- chart of total no. of patients each year -->
                <div>
                    <div><canvas id="numberChart"></canvas></div>
                </div>
                <!-- chart of patient division -->
                <div width="100%" class="" style="overflow: hidden;">
                    <h3 class="m-4">Patients Classification By:</h3>
                    <div width="100%" class="row p-2 d-flex justify-content-around">
                        <!-- division by Age -->
                        <div class="col-lg-3 col-sm-12 p-3 mb-4">
                            <h5>Age</h5>
                            <canvas id="ageGraph"></canvas>
                        </div> 
                        <!-- division by Gender -->
                        <div class="col-lg-3 col-sm-12 p-3 mb-4">
                            <h5>Gender</h5>
                            <canvas id="genderGraph"></canvas>
                        </div>
                        <!-- division by Department -->
                        <div class="col-lg-3 col-sm-12 p-3 mb-4">
                            <h5>Department</h5>
                            <canvas id="departmentGraph"></canvas>
                        </div>
                    </div>
                </div>
                <div class="m-4">
                    <table class="table">
                        <tr>
                            <th>Photo</th>
                            <th>Name</th>
                            <!--<th>Email</th>-->
                            <th>Number</th>
                            <th>Date</th>
                            <th>Visit Time</th>
                            <th>Doctor</th>
                        </tr>
                        <c:forEach var="user" items="${UserData}">
                            <tr>
                                <td>${user.id }</td>
                                <td>${user.name}</td>
                                <td>${user.phone}</td>
                                <td>${user.role}</td>
                                <td>${user.address}</td>
                                <td>${user.gender}</td>
                                <td>${user.age}</td>
                                <td><a href="UserServlet?id="${user.id}>Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </section>
        <script type="module" src="pages/acquisitions.js"></script>
    </body>
</html>

