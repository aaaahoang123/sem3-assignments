<%@ page import="entities.Feedback" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: shink
  Date: 4/8/2019
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Feedback old = (Feedback) request.getAttribute("old");
    if (old == null) {
        old = new Feedback();
    }
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
%>

<form method="post">
    <div class="form-group">
        <label for="username-input">Nội dung</label>
        <textarea type="text" class="form-control"
               id="username-input"
               placeholder="Nhập tên tài khoản"
               name="message" required><%if (old.getMessage() != null) { %><%= old.getMessage()%><%  }%></textarea>
        <%
            if (errors.get("message") != null) {
        %>
        <small class="form-text text-danger"><%= errors.get("message")%></small>
        <%
            }
        %>

    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
