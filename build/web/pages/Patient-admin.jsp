<%-- 
    Document   : Doctor-list
    Created on : Mar 5, 2023, 9:16:37 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient</title>
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

</head>
<body>
    <section class="container-fluid">
        <%@include  file="nav-admin.jsp"%>
        <div class="p-4">
            <div class="row">
                <div class="col">
                    <div class="">
                        <h5>Patient</h5>
                        <small>Admin > Patient List</small>
                    </div>
                </div>
                <div class="col-auto page-action">
                    <button  class="btn btn-light bg-info"
                             type="button"
                             data-toggle="collapse"
                             data-target="#collapseExample"
                             aria-expanded="false"
                             aria-controls="collapseExample">
                        Add Patient
                    </button>
                </div>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Photo</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${patientList}">
                        <tr>
                            <td>${patient.id}</td>
                            <td>${patient.name}</td>
                            <td>${patient.age}</td>
                            <td>${patient.address}</td>
                            <td>${patient.phone}</td>
                            <td>

                                <c:choose>
                                    <c:when test="${patient.email !=null}">
                                        ${patient.email}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" onclick="DeleteAction(${patient.id})" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Confirm Action</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <form action="Controller?page=deletePatient" method="post">
                        <input type="hidden"  name="delete" id="myInput" />
                        <button type="submit" class="btn btn-danger">Confirm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script>
        function DeleteAction(a){
            const deleteForm = document.getElementById("myInput");
            deleteForm.value=a;
        }
        
    </script>
    
</body>
</html>
