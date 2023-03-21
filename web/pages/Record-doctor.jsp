<%-- 
    Document   : AddRecord
    Created on : Mar 21, 2023, 5:36:35 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Records</title>
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
            <div class="container-fluid p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Record</h5>
                            <small>Doctor > Medical Record</small>
                        </div>
                    </div>

                    <div class="row justify-space-between">
                        <div class="col-lg-6 col-md-4 col-sm-12">
                            Hello
                        </div>
                        <div class="col-lg-6 col-md-4 col-sm-12">
                            World
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
