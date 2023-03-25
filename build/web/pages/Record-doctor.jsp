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
                            <span class="text-danger">No report available</span>
                        </div>
                        <div class="col-lg-6 col-md-4 col-sm-12">
                            <form method="post" class="mb-md-2 mt-md-4 pb-2" action="Controller?page=addRecord">
                                <input type="hidden" name="record" value="<%=request.getParameter("record")%>"/>
                                <div class="row gap-3">
                                    <div class="form-outline form-white col-4">
                                        <label for="bp">Blood Pressure</label>
                                        <input type="text" name="bp" id="bp"" placeholder="BP" required/>
                                    </div>
                                    <div class="form-outline form-white col-4 gap-3">
                                        <label for="hb">Heart Beat</label>
                                        <input type="text" id="hb" name="hb" placeholder="HB" required/>
                                    </div>
                                </div>
                                <div class="row space-between">
                                    <div class="form-outline form-white col-4">
                                        <label for="beight">Height</label>
                                        <input type="text" id="height" name="height" placeholder="height" required/>
                                    </div>
                                    <div class="form-outline form-white col-4 gap-3">
                                        <label for="weight">Weight</label>
                                        <input type="number" id="weight" name="weight" placeholder="weight" required/>
                                    </div>
                                </div>
                                <div class="form-outline form-white row">
                                    <label for="symptoms">Symptoms</label>
                                    <textarea name="symptons" id="symptoms" placeholder="symptoms"></textarea>
                                </div>
                                <div class="form-outline form-white row">
                                    <label for="diag">Diagnostics</label>
                                    <textarea name="diagnostics" id="diag" placeholder="Diagnostics" ></textarea>
                                </div>
                                <div class="form-outline form-white row">
                                    <label for="report">Report</label>
                                    <textarea name="report" id="report" placeholder="report" ></textarea>
                                </div>
                                <div class="form-outline form-white row">
                                    <label for="tp">Treatment plan</label>
                                    <textarea name="tp" id="tp" placeholder="tp"></textarea>
                                </div>
                                <div class="form-outline form-white row">
                                    <label for="med">Medication</label>
                                    <textarea name="medication" id="med" placeholder="medication"></textarea><br>
                                </div>
                                <input type="submit" value="Submit" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
