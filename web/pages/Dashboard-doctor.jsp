<%-- 
    Document   : Dashboard
    Created on : Jan 24, 2023, 7:10:14 AM
    Author     : Subin
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="Models.UserParent" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="Base.css"> 
        <link rel="stylesheet" href="index.css">
         <!-- Vendor CSS Files -->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>
        table{
            border-collapse: collapse;
        }
        td{
            padding: 1em;
        }
    </style>
    <body>
        <div class="grid">
            <div>
                <%@ include file="header.jsp" %>
            </div>
            <div class="divider-grid">
                <div>
                    <%@ include  file="nav-doctor.html" %>
                </div>
                <div class="flex">
                    <div class="Users">
                        <div>
                            <div><h3>Users</h3></div>
                            <div>
                                <span>Sort By:</span>
                                <div>
                                    <small>Id</small>
                                    <small>Name</small>
                                    <small>Age</small>
                                </div>
                            </div>
                        </div>
                        <div>
                            <table border="1" border-collapse="collapse">
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th>Role</th>
                                    <th>Address</th>
                                    <th>Gender</th>
                                    <th>Age</th>
                                    <th>Delete</th>
                                </tr>
                                <c:forEach var="user" items="${UserData }">
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
                    <div class="small-grid">
                        <div class="NewPatient">

                        </div>
                        <div class="Calender">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
