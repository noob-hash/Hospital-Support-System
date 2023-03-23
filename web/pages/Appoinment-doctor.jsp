r<%-- 
    Document   : Dashboard
    Created on : Jan 24, 2023, 7:10:14 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appoinment</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon1.png" rel="icon">
        <link href="assets/img/apple-touch-icon_1.png" rel="apple-touch-icon">
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
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <section>
            <%@ include  file="nav-doctor.html" %>
            <div class="p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Appoinments</h5>
                            <small>Doctor > Appoinments</small>
                        </div>
                    </div>

                </div>
                <div class="flex">
                    <div class="Users">
                        <div>
                            <table class="table">
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Gender</th>
                                    <th>Age</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="appoinment" items="${appoinmentList}">
                                    <tr>
                                        <td>${appoinment.user.id}</td>
                                        <td>${appoinment.user.name}</td>
                                        <td>${appoinment.user.gender}</td>
                                        <td>${appoinment.user.age}</td>
                                        <td>${appoinment.sechedule.date}</td>
                                        <td>${appoinment.sechedule.visitTime} - ${appoinment.sechedule.endTime}</td>
                                        <td>
                                            <form method="post" action="Controller?page=record">
                                                <input type="hidden"  name="record" value="${appoinment.user.id}" id="myInput" />
                                                <button type="submit" class="btn btn-info">View Record</button>
                                            </form>
                                        </td>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="small-grid">
                        <div class="NewPatient">

                        </div>
                        <div class="Calender">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
