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
                                <h3>Xóa thông tin người dùng có ID = ${id}</h3>
                            </div>
                            <hr>
                            <div class="alert alert-warning" role="alert">
                                Bạn có chắc muốn xóa người dùng này không ?
                            </div>
                            <form:form action="/admin/user/delete" method="post" modelAttribute="newUser">
                                <div class="mb-3" style="display: none;">
                                    <label class="form-label">ID:</label>
                                    <form:input class="form-control" value="${id}" path="id" type="text" />
                                </div>
                                <input type="submit" class="btn btn-danger" value="Đồng ý" />
                                <a class="btn btn-success" href="/admin/user">Quy lại</a>
                            </form:form>

                        </div>
            </body>

            </html>