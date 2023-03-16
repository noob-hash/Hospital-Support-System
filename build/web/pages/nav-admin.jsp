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
    <form class="nav-item">
        <input
            class="form-control rounded-5"
            style="width: 30vw; "
            type="search"
            placeholder="Search ..."
            aria-label="Search"
            />
    </form>
    <div class="navbar d-flex gap-4">
        <a href="" class="nav-item">
            <i class="fa-sharp fa-regular fa-bell"></i>
        </a>
        <div class="navbar d-flex nav-item">
            <i class="fa-solid fa-user-doctor"></i>
            <i class="fa-sharp fa-solid fa-chevron-down"></i>
        </div>
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
            <a href="Controller?page=logout">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </li>
    </ul>
</nav>
<main class=" bg-light">