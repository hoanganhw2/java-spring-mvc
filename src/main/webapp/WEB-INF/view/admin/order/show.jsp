<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Hoàng anh đại ca</title>

                <link href="/css/style.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Quản lý đơn hàng</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin/order">Trang chính</a></li>
                                    <li class="breadcrumb-item"><a>Đơn hàng</a></li>
                                </ol>

                            </div>
                            <div class="m-5 ">
                                <div class="row">
                                    <div class="col-12 mx-auto table-responsive">
                                        <div class="d-flex justify-content-between">
                                            <h3>Danh sách đơn hàng</h3>

                                        </div>
                                        <hr>
                                        <table class=" table table-bordered table-hover text-center ">
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Người dùng</th>

                                                    <th scope="col">Thanh toán</th>
                                                    <th scope="col">Trạng thái</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="order" items="${orders}">
                                                    <tr>
                                                        <td>${order.id}</td>
                                                        <td>${order.user.fullName}</td>

                                                        <td>
                                                            <fmt:formatNumber type="number"
                                                                value="${order.totalPrice}" />
                                                            VND
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${order.status == 'ĐANG GIAO'}">
                                                                    <span
                                                                        class="bg-warning text-black text-center d-block rounded-5">${order.status}
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${order.status == 'THÀNH CÔNG'}">
                                                                    <span
                                                                        class="bg-success text-white text-center d-block rounded-5">${order.status}
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${order.status == 'HỦY'}">
                                                                    <span
                                                                        class="bg-danger  text-black text-center d-block rounded-5">${order.status}
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${order.status == 'CHỜ XÁC NHẬN'}">
                                                                    <span
                                                                        class="bg-secondary  text-white text-center d-block rounded-5">${order.status}
                                                                    </span>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td class="justify-content-md-around">
                                                            <a class="btn btn-success "
                                                                href="/admin/order/detail/${order.id}">Xem</a>
                                                            <a class="btn btn-warning mx-2"
                                                                href="/admin/order/update/${order.id}">Cập nhật</a>
                                                            <a class="btn btn-danger "
                                                                href="/admin/order/delete/${order.id}">Xóa</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>

                                        </table>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                                <c:forEach begin="0" end="${totalPage}" varStatus="loop">
                                                    <li class="page-item $"><a
                                                            class="${ (loop.index + 1) eq currentPage ? 'active page-link' : 'page-link' }  "
                                                            href=" /admin/order?page=${loop.index+1}">${loop.index +
                                                            1}</a>
                                                    </li>
                                                </c:forEach>
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />

                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                            crossorigin="anonymous"></script>
                        <script src="/js/scripts.js"></script>

            </html>