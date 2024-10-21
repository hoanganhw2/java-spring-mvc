<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="/css/bootstrap/bt_css/bootstrap.min.css">
                <link rel="stylesheet" href="/css/bootstrap/bt_js/bootstrap.bundle.min.js">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <title>Xem thông tin </title>

            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Thông tin chi tiết người dùng ${id}</h3>
                            </div>
                            <hr>
                            <div class="card" style="width: 60%; ">
                                <div class="card-header"> Thông tin</div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">ID:${user.id}</li>
                                    <li class="list-group-item">Email:${user.email}</li>
                                    <li class="list-group-item">Họ tên : ${user.fullName}</li>
                                    <li class="list-group-item">Số điện thoại: ${user.phone}</li>
                                    <li class="list-group-item">Địa chỉ : ${user.address}</li>
                                </ul>

                            </div>
                            <a class="btn btn-primary mt-3" href="/admin/user">Quay lại</a>
                        </div>
                    </div>
            </body>

            </html>