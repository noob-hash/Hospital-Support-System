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
                min-height: 10%;
            }
            nav {
                position: fixed;
                z-index: 2;
                left: 0%;
                top: 10%;
                max-width: 15%;
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
                    <div class="col-auto page-action">
                        <button id="add" class="btn btn-primary">Add Record</button>
                    </div>
                </div>
                <div class="row justify-space-between">
                    <div id="record" class="col-lg-12 col-md-12 col-sm-12">
                        <c:choose>
                            <c:when test="${empty recordList}">
                                <span class="text-danger">No report available</span>

                            </c:when>
                            <c:otherwise>
                                <c:forEach var="record" items="${recordList}">
                                    <div class="">
                                        <div class="row p-4">
                                            <h1>${record.patient.name}</h1>
                                            <small>${record.date}</small>
                                        </div>
                                        <div class="row p-4">
                                            <div class="col-3">
                                                Blood Pressure<br>
                                                ${record.bloodPressure}
                                            </div>
                                            <div class="col-3">
                                                Heart Beat<br>
                                                ${record.heartPressure}
                                            </div>
                                            <div class="col-3">
                                                Height<br>
                                                ${record.height}
                                            </div>
                                            <div class="col-3">
                                                Weight<br>
                                                ${record.weight}
                                            </div>
                                        </div>
                                        <div class="row p-4">
                                            <b>Symptoms</b>
                                            ${record.symptoms}
                                        </div>
                                        <div class="row p-4">
                                            <b>Diagnosis</b>
                                            ${record.diagnosis}
                                        </div>
                                        <div class="row p-4">
                                            <b>Treatment</b>
                                            ${record.treatment}
                                        </div>
                                        <div class="row p-4">
                                            <b>Medication</b>
                                            ${record.medicines}
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div id="form" class="col-lg-6 col-md-4 col-sm-12" style="display:none">
                        <div class="d-flex">
                            <div class="vr"></div>
                            
                            <form method="post" class="mb-md-2 mt-md-4 p-4 form-control" action="Controller?page=addRecord">
                            <input required type="hidden" name="record" value="<%=request.getParameter("record")%>"/>
                            <div class="row justify-content-between">
                                <h3 class="col">Add New Record</h3>
                                <button type="button" class=" btn bg-danger col-auto" id="close">
                                <i class="fa-solid fa-x"></i>
                            </button>
                            </div>
                            <div class="row justify-content-around">
                                <div class="form-outline form-white col-4">
                                    <label for="bp">Blood Pressure</label>
                                    <input  type="text" name="bp" id="bp"" placeholder="BP" required/>
                                </div>
                                <div class="form-outline form-white col-4 gap-3">
                                    <label for="hb">Heart Beat</label>
                                    <input  type="text" id="hb" name="hb" placeholder="HB" required/>
                                </div>
                            </div>
                            <div class="row justify-content-around">
                                <div class="form-outline form-white col-4">
                                    <label for="height">Height</label>
                                    <input  type="text" id="height" name="height" placeholder="height" required/>
                                </div>
                                <div class="form-outline form-white col-4 gap-3">
                                    <label for="weight">Weight</label>
                                    <input  type="number" id="weight" name="weight" placeholder="weight" required/>
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
                            <div class="row mt-4 justify-content-center">
                                <input class="btn btn-primary col-auto" type="submit" value="Submit" />
                            </div>
                        </form>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        document.getElementById("add").addEventListener('click', () => {
            document.getElementById("form").style.display = "block";
            document.getElementById("record").className = "col-lg-6 col-md-4 col-sm-12";
        });
        document.getElementById("close").addEventListener('click', () => {
            document.getElementById("form").style.display = "none";
            document.getElementById("record").className = "col-lg-12 col-md-12 col-sm-12";
        });
    </script>
</body>
</html>
