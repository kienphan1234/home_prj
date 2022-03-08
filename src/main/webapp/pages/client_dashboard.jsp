<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Client</title>
    <jsp:include page="library.jsp" />
    <script src="${pageContext.request.contextPath}/resources/js/header.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/client_total.js" ></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
</head>
<body>
<div class="spinner-border" id="loader"></div>

<div class="wrapper" style="display: none">
    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3><i class="fa fa-fw fa-home"><a href="/client/dashboard"></a></i>Home Management</h3>
        </div>

        <ul class="list-unstyled components sticky-top">
            <li class="active">
                <a href="${pageContext.request.contextPath}/client/dashboard"><i class="fa fa-fw fa-globe"></i>Trang chủ</a>
            </li>
            <li>
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-fw fa-search"></i>Tra cứu</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="#">Tổng tiền trọ hàng tháng</a>
                    </li>
                    <li>
                        <a href="#">Tổng tiền điện hàng tháng</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-comment-o"></i>Phản hồi/Đóng góp</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-envelope"></i>Liên hệ</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-wrench"></i>Thiết định</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-user"></i>Thông tin phòng trọ</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content  -->
    <div id="content">
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <div class="container">
            <h2 class="text-center">Tiền trọ hàng tháng</h2>
            <form id="frmSearchClientTotal" style="margin-bottom: 100px">
                <div class="form-row d-flex justify-content-around align-items-center align-self-center">
                    <div class="col-md-2">
                        <label class="form-label">Năm</label>
                        <select id="year" name="year" class="select2 form-control">
                            <option value="">Tất cả</option>
                            <option value="2022">Năm 2022</option>
                            <option value="2021">Năm 2021</option>
                            <option value="2020">Năm 2020</option>
                            <option value="2019">Năm 2019</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Tháng</label>
                        <select id="month" name="month" class="select2 form-control">
                            <option value="">Tất cả</option>
                            <option value="12">Tháng 12</option>
                            <option value="11">Tháng 11</option>
                            <option value="10">Tháng 10</option>
                            <option value="09">Tháng 9</option>
                            <option value="08">Tháng 8</option>
                            <option value="07">Tháng 7</option>
                            <option value="06">Tháng 6</option>
                            <option value="05">Tháng 5</option>
                            <option value="04">Tháng 4</option>
                            <option value="03">Tháng 3</option>
                            <option value="02">Tháng 2</option>
                            <option value="01">Tháng 1</option>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <div class="form-check-inline">
                            <label class="form-check-label" for="notPay">
                                <input type="radio" class="form-check-input" id="allPay" name="status" value="" checked>Tất cả
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label" for="notPay">
                                <input type="radio" class="form-check-input" id="notPay" name="status" value="0">Chưa nộp
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label" for="pay">
                                <input type="radio" class="form-check-input" id="pay" name="status" value="1">Đã nộp
                            </label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="">
                            <button type="button" id="btnSearch" class="btn btn-search" data-current_page="${currentPage}" onclick="searchTotalPriceClient(this)">Tìm kiếm</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Tiền nhà</th>
                            <th>Tiền điện</th>
                            <th>Tiền nước</th>
                            <th>Tiền mạng</th>
                            <th>Tiền rác</th>
                            <th>Phí sinh hoạt</th>
                            <th>Tổng</th>
                            <th>Trạng thái</th>
                            <th>Ghi chú</th>
                            <th>Ngày nộp</th>
                        </tr>
                    </thead>
                    <tbody id="priceListTable">
                        <c:forEach items="${homePriceList}" var="price">
                            <tr>
                                <td>Tháng ${price.createdAt}</td>
                                <td><c:out value="${price.roomPrice}" /></td>
                                <td><c:out value="${price.electricPrice}" /></td>
                                <td><c:out value="${price.waterPrice}"/></td>
                                <td><c:out value="${price.internetPrice}" /></td>
                                <td><c:out value="${price.garbagePrice}" /></td>
                                <td><c:out value="${price.livingPrice}" /></td>
                                <td><c:out value="${price.total}" /></td>
                                <td>${price.status == 0 ? 'Chưa nộp' : 'Đã nộp'}</td>
                                <td><c:out value="${price.note}" /></td>
                                <td>${price.depositedAt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${totalPage > 1}">
                <ul id="pagination" class="pagination">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item">
                            <a class="page-link" onclick="searchTotalPriceClient(this)" href="#" data-current_page="${currentPage - 1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${totalPage}">
                        <li class="page-item ${i == currentPage ? ' active' : ''}">
                            <a class="page-link" onclick="searchTotalPriceClient(this)" href="#" data-current_page="${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${currentPage < totalPage}">
                        <li class="page-item">
                            <a class="page-link" onclick="searchTotalPriceClient(this)" href="#" data-current_page="${currentPage + 1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
