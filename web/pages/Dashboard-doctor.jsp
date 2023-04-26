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
            overflow-x: hidden;
        }
    </style>
    
    
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>
    
    <body>
        <section>
            <%@ include  file="nav-doctor.html" %>
            <div class="p-4">
                <div class="row">
                    <div class="col">
                        <div class="">
                            <h5>Dashboard</h5>
                            <small>Doctor > Dashboard</small>
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
                    </div>
                </div>
            </div>
        </div>
    </section>
            <script>
                const numChart = document.getElementById('numberChart');
            const ageGraph = document.getElementById('ageGraph');
            const genderChart = document.getElementById('genderGraph');

            new Chart(numChart, {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
                    datasets: [{
                            label: new Date().getFullYear(),
                            data: ${YearAppoinment},
                            borderWidth: 1
                        },
                        {
                            label: new Date().getFullYear() -1,
                            data: ${PYearAppoinment},
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    elements: {
                        arc: {
                            hoverOffset: 15
                        }
                    }
                }
            });

            new Chart(genderChart, {
                type: 'pie',
                data: {
                    labels: ['Male', 'Female'],
                    datasets: [{
                            data: [${gender[1]},${gender[0]}],
                        }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            display: false
                        },
                    },
                    elements: {
                        arc: {
                            hoverOffset: 15
                        }
                    }
                }


            });
            new Chart(ageGraph, {
                type: 'doughnut',
                data: {
                    labels: ['0-15', '15-30', '30-45', '45-60', '60-75', '75-90', '90+'],
                    datasets: [{
                        
                            data: ${age},
                        }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            display: false
                        },
                    },
                    elements: {
                        arc: {
                            hoverOffset: 15
                        }
                    }
                }
            });
            </script>
</body>
</html>
