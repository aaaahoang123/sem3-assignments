package controllers;

import models.FeedbacksModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-feedback")
public class DeleteFeedback extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idFromReq = req.getParameter("id");
        if (idFromReq != null) {
            int id = Integer.parseInt(idFromReq);
            try {
                new FeedbacksModel().removeById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            resp.sendRedirect("./home");
        }
    }
}
