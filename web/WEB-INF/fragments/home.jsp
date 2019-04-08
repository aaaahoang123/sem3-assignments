<%@ page import="java.util.List" %>
<%@ page import="entities.Feedback" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Account" %>
<%@ page import="utils.auth.AuthImpl" %><%--
  Created by IntelliJ IDEA.
  User: shink
  Date: 4/8/2019
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Feedback> data = (List<Feedback>) request.getAttribute("data");
    if (data == null) {
        data = new ArrayList<>();
    }
    Account account = new AuthImpl(request).getAccount();
%>
<h3 class="my-3">Danh sách feedback</h3>
<ul class="list-unstyled">
    <%
        for (Feedback item: data) {
            %>
                <li class="media">
                    <img src="https://via.placeholder.com/64" class="mr-3" alt="...">
                    <div class="media-body">
                        <h5 class="mt-0 mb-1"><%= item.getAccount().getFullName()%></h5>
                        <%= item.getMessage()%>
                    </div>
                    <%
                        if (account.getRole() == 1) {
                            %><a class="btn btn-danger" href="delete-feedback?id="<%=item.getId()%>>Xóa</a><%
                        }
                    %>

                </li>

    <%
        }
    %>
</ul>
