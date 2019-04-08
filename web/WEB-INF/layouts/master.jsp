<%--
  Created by IntelliJ IDEA.
  User: shink
  Date: 4/8/2019
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="/wc_assignment_war_exploded/">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">

    <title>Hello, world!</title>
</head>
<body>
<%
    String contentPath = (String) request.getAttribute("content");
    contentPath = "../fragments/" + contentPath + ".jsp";
%>
<jsp:include page="header.jsp" />
<main class="container mt-2">
    <jsp:include page="<%= contentPath%>" />
</main>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="assets/js/jquery-3.3.1.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
