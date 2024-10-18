<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="/css/bootstrap/bt_css/bootstrap.min.css">
            <link rel="stylesheet" href="/css/bootstrap/bt_js/bootstrap.bundle.min.js">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <title>Document</title>
        </head>

        <body>
            <h1>${test}</h1>
            <h2>${test2}</h2>
            <form action="/admin/create" method="get">
                <input class="btn btn-primary" type="submit" value="Thêm user">
            </form>
        </body>

        </html>