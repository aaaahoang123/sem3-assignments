package controllers;

import entities.Account;
import models.AccountsModel;
import utils.MyConstant;
import utils.auth.Auth;
import utils.auth.AuthImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Đăng nhập");
        req.setAttribute("content", "login");
        req.getRequestDispatcher(MyConstant.BASE_VIEW + "layouts/master.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || username.length() < 1 || password.length() < 1) {
            errorLogin(req, resp);
            return;
        }

        Map<String, Object> conditions = new HashMap<>();
        conditions.put("username", username);
        try {
            Account account = new AccountsModel().findByConditions(conditions).get(0);
            if (account.getPassword().equals(password)) {
                Auth auth = new AuthImpl(req);
                auth.login(account);
                resp.sendRedirect("./home");
                return;
            }

            errorLogin(req, resp);
        } catch (SQLException | InstantiationException | IllegalAccessException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            errorLogin(req, resp);
        }
    }

    private void errorLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "Sai thông tin đăng nhập");
        doGet(req, resp);
    }
}
