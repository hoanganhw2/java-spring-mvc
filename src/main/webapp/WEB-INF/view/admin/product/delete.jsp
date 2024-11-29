<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Xóa sản phẩm</title>
                <link rel="stylesheet" href="/css/bootstrap/bt_css/bootstrap.min.css">
                <link rel="stylesheet" href="/css/bootstrap/bt_js/bootstrap.bundle.min.js">
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
                                <h1 class="mt-4">Dashboard</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin/user">Quản lý sản phẩm</a></li>
                                    <li class="breadcrumb-item active">Sản phẩm </li>
                                    <li class="breadcrumb-item active">Xóa </li>

                                </ol>
                            </div>
                            <div class="container mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Xóa thông tin sản phẩm có ID = ${id}</h3>
                                        </div>
                                        <hr>
                                        <div class="alert alert-warning" role="alert">
                                            Bạn có chắc muốn xóa sản phẩm này không ?
                                        </div>
                                        <form:form action="/admin/product/delete" method="post"
                                            modelAttribute="newProduct">
                                            <div class="mb-3" style="display: none;">
                                                <label class="form-label">ID:</label>
                                                <form:input class="form-control" value="${id}" path="id" type="text" />
                                            </div>
                                            <input type="submit" class="btn btn-danger" value="Đồng ý" />
                                            <a class="btn btn-success" href="/admin/product">Quy lại</a>
                                        </form:form>

                                    </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </html>