<%@ page import="entities.Account" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: shink
  Date: 4/8/2019
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Account old = (Account) request.getAttribute("old");
    if (old == null) {
        old = new Account();
    }

    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
%>

<form method="post">
    <div class="form-group">
        <label for="username-input">Tài khoản</label>
        <input type="text" class="form-control"
               id="username-input"
               placeholder="Nhập tên tài khoản"
               name="username"
               value="<%= old.getUsername() != null ? old.getUsername() : ""%>"
               required>
        <%
            if (errors.get("username") != null) {
                %>
                    <small class="form-text text-danger"><%= errors.get("username")%></small>
                <%
            }
        %>

    </div>
    <div class="form-group">
        <label for="password-input">Mật khẩu</label>
        <input type="password" class="form-control" id="password-input" placeholder="Nhập mật khẩu" name="password" required
               value="<%= old.getPassword() != null ? old.getPassword() : ""%>"
        >
        <%
            if (errors.get("password") != null) {
        %>
        <small class="form-text text-danger"><%= errors.get("password")%></small>
        <%
            }
        %>
    </div>
    <div class="form-group">
        <label for="name-input">Tên</label>
        <input type="text" class="form-control" id="name-input" placeholder="Nhập tên" name="fullName"
               value="<%= old.getFullName() != null ? old.getFullName() : ""%>"
        >
    </div>
    <div class="form-group">
        <label for="email-input">Email</label>
        <input type="email" class="form-control" id="email-input" placeholder="Nhập email" required name="email"
               value="<%= old.getEmail() != null ? old.getEmail() : ""%>"
        >
    </div>
    <%
        if (errors.get("email") != null) {
            %><small class="form-text text-danger"><%= errors.get("email")%></small><%
        }
    %>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
