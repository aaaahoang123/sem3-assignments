package utils.auth;

import entities.Account;

public interface Auth {
    Account getAccount();
    boolean isLogin();
    void login(Account account);
    void logout();
}
