package controllers;

import models.FeedbacksModel;
import utils.MyConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/home")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Home");
        req.setAttribute("content", "home");
        try {
            req.setAttribute("data", new FeedbacksModel().getList());
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(MyConstant.BASE_VIEW + "layouts/master.jsp").forward(req, resp);
    }
}
