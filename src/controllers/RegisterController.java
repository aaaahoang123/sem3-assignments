package controllers;

import entities.Account;
import models.AccountsModel;
import models.BaseModel;
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
import java.util.Map;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Đăng ký");
        req.setAttribute("content", "register");
        req.getRequestDispatcher(MyConstant.BASE_VIEW + "layouts/master.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = new Account(req);
        Map<String, String> validator = account.validate();
        if (validator.keySet().size() > 0) {
            req.setAttribute("old", account);
            req.setAttribute("errors", validator);
            doGet(req, resp);
            return;
        }

        BaseModel<Account> model = new AccountsModel();
        try {
            Account result = model.insertOne(account);
            Auth auth = new AuthImpl(req);
            auth.login(result);
            resp.sendRedirect("./home");
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            req.setAttribute("old", account);
            doGet(req, resp);
        }
    }
}
