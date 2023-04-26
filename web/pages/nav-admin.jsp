<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header
    class="navbar bg-white position-fixed "
    style="padding-inline: 2em; padding-top: 0;"
    >
    <div>
        <span
            style="font-family: 'Montserrat', sans-serif; font-weight: bolder"
            >
            <b>H</b>ospital</span
        >
    </div>
    <form class="nav-item" action="Controller?page=search" method="post">
        <input
            class="form-control rounded-5"
            style="width: 30vw; "
            type="search"
            placeholder="Search ..."
            name="Search"
            aria-label="Search"
            list ="datalist"
            />
        <datalist id="datalist">
            <c:forEach items="${List}" var="list">
                <option value="${list.name}">${list.name}</option>
            </c:forEach>
        </datalist>

    </form>
    <div class="navbar d-flex gap-4">
        <a href="" class="nav-item">
            <i class="fa-sharp fa-regular fa-bell"></i>
        </a>
        <a href="Controller?page=editProfile" class="navbar d-flex nav-item">
            <i class="fa-solid fa-user-doctor"></i>
            <i class="fa-sharp fa-solid fa-chevron-down"></i>
        </a>
    </div>
</header>
<!--auzaar-->
<nav class="nav p-4">
    <ul class="nav navbar-nav gap-4 mt-4">
        <li class="nav-item">
            <a href="Controller?page=dashboard">
                <i class="fa-solid fa-temperature-half"></i>
                Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a href="Controller?page=appoinmentPage">
                <i class="fa-sharp fa-solid fa-stethoscope"></i>
                Appoinment
            </a>
        </li>
        <li class="nav-item">
            <a href="Controller?page=doctorList">
                <i class="fa-sharp fa-solid fa-user-doctor"></i>
                Doctors
            </a>
        </li>
        <li class="nav-item">
            <a href="Controller?page=department">
                <i class="fa-solid fa-hospital"></i>
                Departments
            </a>
        </li>
        <li class="nav-item">
            <a href="Controller?page=patient">
                <i class="fa-solid fa-bed-pulse"></i>
                Patients
            </a>
        </li>
        <li class="nav-item">
            <a href="Controller?page=logout" onclick="preventBack()">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </li>
    </ul>
</nav>
<script>
    function preventBack() {
        window.history.forward();
    }
    setTimeout("preventBack()", 0);
    window.onunload = function () {
        null;
    };
</script>
<main class=" bg-light">