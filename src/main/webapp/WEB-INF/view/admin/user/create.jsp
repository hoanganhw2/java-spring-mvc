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
                <title>Thêm mới người dùng </title>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Thêm mới người dùng</h3>
                            </hr>
                            <form:form method="post" action="/admin/user/create1" modelAttribute="newUser">
                                <div class="mb-3">
                                    <label class="form-label">Email:</label>
                                    <form:input type="email" class="form-control" path="email" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Mật khẩu:</label>
                                    <form:input type="password" class="form-control" path="password" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Số điện thoại:</label>
                                    <form:input class="form-control" type="tel" path="phone" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Họ tên :</label>
                                    <form:input type="text" class="form-control" path="fullName" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Địa chỉ:</label>
                                    <form:input type="text" class="form-control" path="address" />
                                </div>
                                <div class="mb-3">
                                    <input type="submit" value="Thêm" class="btn btn-primary" />
                                </div>
                            </form:form>


                        </div>
                    </div>
                </div>
            </body>

            </html>