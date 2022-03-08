<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">

        <button type="button" id="sidebarCollapse" class="btn btn-info">
            <i class="fa fa-fw fa-bars"></i>
            <span>Menu</span>
        </button>
        <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-align-justify"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav ml-auto sticky-top">
                <li class="nav-item active mr-2">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-fw fa-user"></i>User</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Thay đổi thông tin cá nhân</a>
                        <a class="dropdown-item" href="#">Thay đổi mật khẩu</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" onclick="logoutSubmit()">
                        <i class="fa fa-sign-out"></i>Log out
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
