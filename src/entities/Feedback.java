package entities;

import models.AccountsModel;
import utils.MyConstant;
import utils.auth.AuthImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Feedback {
    private int id;
    private int accountId;
    private String message;
    private long createdAt;
    private long updatedAt;
    private int status;

    public Feedback() {
    }

    public Feedback(int id, int accountId, String message, long createdAt, long updatedAt, int status) {
        this.id = id;
        this.accountId = accountId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Feedback(HttpServletRequest req) {
        accountId = new AuthImpl(req).getAccount().getId();
        message = req.getParameter("message");
        long now = System.currentTimeMillis();
        createdAt = now;
        updatedAt = now;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> validate() {
        Map<String, String> result = new HashMap<>();

        if (message == null || message.length() < 1) {
            result.put("message", "Nội dung không được để trống");
        }

        return result;
    }

    public Account getAccount() {
        try {
            return new AccountsModel().findById(this.accountId);
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
