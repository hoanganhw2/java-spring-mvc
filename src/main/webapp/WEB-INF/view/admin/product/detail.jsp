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
                <title>Chi tiết sản phẩm</title>
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
                                <h1 class="mt-4">Quản lý sản phẩn</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Trangc chính</a></li>
                                    <li class="breadcrumb-item active">Sản phẩm</li>
                                    <li class="breadcrumb-item active">Thông tin</li>
                                </ol>
                            </div>
                            <!-- <div class="container mt-5">
                            <div class="row">
                                <div class="col-12 mx-auto">
                                    <div class="d-flex justify-content-between">
                                        <h3>Thông tin chi tiết sản phẩm ${id}</h3>
                                    </div>
                                    <hr>
                                    <div>
                                        <img class="card-img-top" style="width: 100px; height: auto;"
                                            src="/images/product/${product.image}" alt="Card image cap">

                                        <div class="card  " style="width: 60%; ">
                                            <div class="card-header"> Thông tin</div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID:${product.id}</li>
                                                <li class="list-group-item">Tên:${product.name}</li>
                                                <li class="list-group-item">Giá : ${product.price}</li>
                                            </ul>

                                        </div>
                                    </div>
                                    <a class="btn btn-primary mt-3" href="/admin/product">Quay lại</a>
                                </div>
                            </div>
                        </div> -->
                            <div class="container mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Thông tin chi tiết sản phẩm ${id}</h3>
                                        </div>
                                        <hr>
                                        <div>
                                            <div class="card" style="width: 60%; margin: auto;">
                                                <img class="card-img-top" src="/images/product/${product.image}"
                                                    alt="Card image cap" style="width: 100%; height: auto;">

                                                <div class="card-header">Thông tin</div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item">ID: ${product.id}</li>
                                                    <li class="list-group-item">Tên: ${product.name}</li>
                                                    <li class="list-group-item">Giá:
                                                        <fmt:formatNumber type="number" value="${product.price}" />VND
                                                    </li>
                                                    <li class="list-group-item">Cấu hình: ${product.shortDesc}</li>
                                                    <li class="list-group-item">Chi tiết: ${product.detailDesc}</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <a class="btn btn-primary mt-3" href="/admin/product">Quay lại</a>
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