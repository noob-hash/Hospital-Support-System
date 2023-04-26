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
        .hidden-depvalue{
            display: none !important;
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js" ></script>
    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
        integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer"
        />

    <link
        rel="stylesheet"
        href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"

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
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4 d-flex gap-2 align-items-center">
                        <div>
                            <i class="fa-sharp fa-solid fa-stethoscope"></i>
                        </div>
                        <div>
                            <div><b>Doctors</b></div>
                            <div><b>${TotalDoc}</b></div>
                        </div>
                    </div>
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4 d-flex gap-2 align-items-center">
                        <div>
                            <i class="fa-solid fa-user-doctor"></i>
                        </div>
                        <div>
                            <div><b>Total Appoinments</b></div>
                            <div><b>${TotalAppoinment}</b></div>
                        </div>
                    </div>
                    <div class="shadow bg-white rounded col-lg-2 col-sm-12 p-3 mb-4 d-flex gap-2 align-items-center">
                        <div>
                            <i class="fa-solid fa-user-doctor"></i>
                        </div>
                        <div>
                            <div><b>Total Patients</b></div>
                            <div><b>${TotalNo}</b></div>
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
                <div id="dep">
                <c:forEach var="dep" items="${depName}">
                    <div class="hidden-depvalue">
                        ${dep}
                    </div>
                </c:forEach>
                    </div>
                <div class="m-4">
                    <table id="tableData" class="table display">
                        <thead>
                            <tr>
                                <th>Photo</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Address</th>
                                <th>Gender</th>
                                <th>Age</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${UserData}">
                                <tr>
                                    <td>${user.id }</td>
                                    <td>${user.name}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.email}</td>
                                    <td>${user.role}</td>
                                    <td>${user.address}</td>
                                    <td>${user.gender}</td>
                                    <td>${user.age}</td>

                                </tr>
                            </c:forEach>
                                <c:forEach var="user" items="${depPatient}">
                                <script>console.log(${user.columnName});</script>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <script>
            $(document).ready(function () {
                $('#tableData').DataTable();
            });
        </script>
        <!--        <script type="module" src="pages/acquisitions.js"></script>-->
        <script>
            
            arr = [];
            var element=document.querySelectorAll(".hidden-depvalue");
            for(let i=0 ; i< element.length;i++){
                arr.push(element[i].innerText);
            }
            const numChart = document.getElementById('numberChart');
            const ageGraph = document.getElementById('ageGraph');
            const genderChart = document.getElementById('genderGraph');
            const depGraph = document.getElementById('departmentGraph');

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
            new Chart(depGraph, {
                type: 'polarArea',
                data: {
                    labels: arr,
                    datasets: [{
                            data: ${depPCount},
                            backgroundColor: ["#36A2EB", "#18a3ac", "#178ccb", "#99a164", "#7cd4f4", "#d7c7ac"]
                        }],
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            display: false
                        },
                    },
                    scales: {
                        r: {
                            ticks: {
                                display: false // Remove vertical numbers
                            },
                            grid: {
                                display: false // Removes the circulair lines
                            }
                        }
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

