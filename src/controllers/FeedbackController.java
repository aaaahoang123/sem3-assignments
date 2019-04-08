package controllers;

import entities.Feedback;
import models.BaseModel;
import models.FeedbacksModel;
import utils.MyConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/feedback")
public class FeedbackController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Gửi feedback");
        req.setAttribute("content", "feedback");
        req.getRequestDispatcher(MyConstant.BASE_VIEW + "layouts/master.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Feedback feedback = new Feedback(req);

        Map<String, String> validator = feedback.validate();
        if (validator.keySet().size() > 0) {
            req.setAttribute("old", feedback);
            req.setAttribute("errors", validator);
            doGet(req, resp);
        }

        BaseModel<Feedback> model = new FeedbacksModel();
        try {
            model.insertOne(feedback);
            resp.sendRedirect("./");
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            req.setAttribute("old", feedback);
            doGet(req, resp);
        }
    }
}
