package utils.auth;

import entities.Account;
import models.AccountsModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AuthImpl implements Auth {
    private HttpServletRequest req;
    private String sessionKey = "akjfhajfka";
    public AuthImpl(HttpServletRequest req) {
        this.req = req;
    }

    @Override
    public Account getAccount() {
        Integer id = (Integer) this.req.getSession().getAttribute(sessionKey);
        if (id != null) {
            try {
                return new AccountsModel().findById(id);
            } catch (SQLException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean isLogin() {
        return this.req.getSession().getAttribute(sessionKey) != null;
    }

    @Override
    public void login(Account account) {
        this.req.getSession().setAttribute(this.sessionKey, account.getId());
    }

    @Override
    public void logout() {
        this.req.getSession().removeAttribute(this.sessionKey);
    }
}
