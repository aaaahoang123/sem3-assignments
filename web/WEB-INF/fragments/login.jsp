<%@ page import="entities.Account" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
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

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= error%>
                </div>
                <%
            }
        %>

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
        <input type="password" class="form-control"
               id="password-input"
               placeholder="Nhập mật khẩu"
               name="password" required
               value="<%= old.getPassword() != null ? old.getPassword() : ""%>"
        >
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
