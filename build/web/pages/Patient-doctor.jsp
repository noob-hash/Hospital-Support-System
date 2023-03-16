<%-- 
    Document   : Doctor-list
    Created on : Mar 5, 2023, 9:16:37 AM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patients</title>
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
    </section>
</body>
</html>
