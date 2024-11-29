<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            <h1 class="mt-4">Quản lý người dùng</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Trang chính</a></li>
                                <li class="breadcrumb-item active">Người dùng</li>
                            </ol>
                        </div>
                        <div class=" m-5">
                            <div class="row">
                                <div class="col-12 mx-auto">
                                    <div class="d-flex justify-content-between">
                                        <h3>Danh sách người dùng</h3>
                                        <a class="btn btn-primary" href="/admin/user/create">Thêm mới </a>
                                    </div>
                                    <hr>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover ">
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Email</th>
                                                    <th scope="col">Họ tên</th>
                                                    <th scope="col">ROLE</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="user" items="${users}">
                                                    <tr>
                                                        <td>${user.id}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.fullName}</td>
                                                        <c:choose>
                                                            <c:when test="${user.role.name == 'ADMIN'}">
                                                                <td class="text-danger fw-b f-2">${user.role.name}
                                                                </td>
                                                            </c:when>
                                                            <c:when test="${user.role.name == 'USER'}">
                                                                <td class="text-success fw-b f-2">${user.role.name}
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>${user.role.name}</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td>
                                                            <a class="btn btn-success "
                                                                href="/admin/user/${user.id}">Xem</a>
                                                            <a class="btn btn-warning mx-2"
                                                                href="/admin/user/update/${user.id}">Sửa</a>
                                                            <a class="btn btn-danger "
                                                                href="/admin/user/delete/${user.id}">Xóa</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item">
                                                    <a class="${ 1 eq currentPage ? 'disabled page-link' : 'page-link' } "
                                                        href="/admin/user?page=${currentPage - 1 }"
                                                        aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                                <c:forEach begin="0" end="${totalPage}" varStatus="loop">
                                                    <li class="page-item $"><a
                                                            class="${ (loop.index + 1) eq currentPage ? 'active page-link' : 'page-link' }  "
                                                            href=" /admin/user?page=${loop.index+1}">${loop.index +
                                                            1}</a>
                                                    </li>
                                                </c:forEach>

                                                <a class="${ (totalPage + 1) eq currentPage ? 'disabled page-link' : 'page-link' }"
                                                    href="/admin/user?page=${currentPage + 1 }" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
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