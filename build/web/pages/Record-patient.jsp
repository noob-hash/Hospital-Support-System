<%-- 
    Document   : Record-patient
    Created on : Apr 2, 2023, 6:55:55 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug.js"></script>
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
                max-width: 85%;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <section>
            <%@ include  file="nav-patient.html" %>
            <div class="container-fluid p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Record</h5> 
                            <small>Patient > Medical Record</small>
                        </div>
                    </div>
                    <div class="col-auto page-action">
                        <button id="gpdf">Download</button>
                    </div>
                </div>
            </div>
            <hr class="m-0 p-0">
            <div class=" p-4 pt-0 container-fluid">
                <div class="PDFcontent">
                    <c:choose>
                        <c:when test="${empty recordList}">
                            <span class="text-danger">No report available</span>

                        </c:when>
                        <c:otherwise>
                            <c:forEach var="record" items="${recordList}">
                                <div class="" id="${record.date}">
                                    <div class="row p-4">
                                        <h1>${record.patient.name}</h1>
                                        <small>${record.date}</small>
                                    </div>
                                    <div class="row p-4 justify-content-around">
                                        <div class="col-lg-2 col-sm-12 p-4 border bg-white border-outline-dark">
                                            Blood Pressure<br>
                                            ${record.bloodPressure}
                                        </div>
                                        <div class="col-lg-2 col-sm-12 p-4 border bg-white border-outline-dark">
                                            Heart Beat<br>
                                            ${record.heartPressure}
                                        </div>
                                        <div class="col-lg-2 col-sm-12 p-4 border bg-white border-outline-dark">
                                            Height<br>
                                            ${record.height}
                                        </div>
                                        <div class="col-lg-2 col-sm-12 p-4 border bg-white border-outline-dark">
                                            Weight<br>
                                            ${record.weight}
                                        </div>
                                    </div>
                                    <div class="row mb-4 p-4 border bg-white border-outline-dark">
                                        <b>Symptoms</b>
                                        ${record.symptoms}
                                    </div>
                                    <div class="row mb-4 p-4 border bg-white border-outline-dark">
                                        <b>Diagnosis</b>
                                        ${record.diagnosis}
                                    </div>
                                    <div class="row mb-4 p-4 border bg-white border-outline-dark">
                                        <b>Treatment</b>
                                        ${record.treatment}
                                    </div>
                                    <div class="row p-4 border bg-white border-outline-dark">
                                        <b>Medication</b>
                                        ${record.medicines}
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>


                </div>
            </div>

            <div class="ignoreContent">

                <p>Only for display and not in pdf</p>

            </div>
        </div>
    </section>
    <script>

        var pdfdoc = new jsPDF();
        var specialElementHandlers = {
            '.ignoreContent': function (element, renderer) {
                return true;
            }
        };


        $(document).ready(function () {
            $("#gpdf").click(function () {
                pdfdoc.fromHTML($('.PDFcontent').html(), 10, 10, {
                    'width': 110,
                    'elementHandlers': specialElementHandlers
                });

                pdfdoc.save('First.pdf');

            });
        });

    </script>
</body>
</html>
