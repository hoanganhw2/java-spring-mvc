<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
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
                                <div class="m-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <div class="d-flex justify-content-between">
                                                <h3>Sửa đơn hàng có mã đơn là ${orderId}</h3>

                                            </div>
                                            <hr>
                                            <form:form method="post" action="/admin/order/update"
                                                modelAttribute="order">
                                                <div class="mb-3 " style="display: none;">
                                                    <label class="form-label">Id:</label>
                                                    <form:input class="form-control" path="id" type="text" />
                                                </div>
                                                <div class="mb-3 ">
                                                    <label class="form-label">Id người dùng đặt hàng:</label>
                                                    <input class="form-control" value="${order.user.id}" type="text"
                                                        disabled="true" />
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Tên người nhận:</label>
                                                    <form:input type="text" class="form-control" path="receiverName"
                                                        disabled="true" />
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Số điện thoại:</label>
                                                    <form:input class="form-control" type="tel" path="receiverPhone"
                                                        disabled="true" />
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Địa chỉ:</label>
                                                    <form:input type="text" class="form-control" path="receiverAddress"
                                                        disabled="true" />
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Số tiền thanh toán:</label>
                                                    <fmt:formatNumber type="number" value="${order.totalPrice}"
                                                        var="fmPrice" />
                                                    <input type="text" class="form-control" value="${fmPrice}"
                                                        disabled="true" />

                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Trạng thái giao hàng:</label>
                                                    <form:select class="form-select" path="status">

                                                        <form:option value="THÀNH CÔNG">Thành
                                                            công
                                                        </form:option>
                                                        <form:option value="ĐANG GIAO">Đang giao</form:option>
                                                        <form:option value="HỦY">Hủy giao hàng
                                                        </form:option>
                                                        <form:option value="CHỜ XÁC NHẬN"> Chờ xác nhận
                                                        </form:option>

                                                    </form:select>
                                                </div>
                                                <div class="mb-3 d-flex justify-content-between">
                                                    <input type="submit" value="Cập nhật"
                                                        class="btn btn-primary me-2" />
                                                    <a class="btn btn-success " href="/admin/order">Quay lại</a>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>

                </html>