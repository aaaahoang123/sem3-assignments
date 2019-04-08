package entities;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private int role;
    private long createdAt;
    private long updatedAt;
    private int status;

    public Account() {
    }

    public Account(HttpServletRequest req) {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.fullName = req.getParameter("fullName");
        this.email = req.getParameter("email");
        long now = System.currentTimeMillis();
        createdAt = now;
        updatedAt = now;
    }

    public Account(int id, String username, String password, String fullName, String email, long createdAt, long updatedAt, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Map<String, String> validate() {
        Map<String, String> result = new HashMap<>();
        if (this.username == null || this.username.length() < 6) {
            result.put("username", "Tên tài khoản phải có ít nhất 6 ký tự");
        }
        if (this.password == null || this.password.length() < 6) {
            result.put("password", "Mật khẩu phải có ít nhất 6 ký tự");
        }
        if (this.email == null || this.email.length() < 1) {
            result.put("email", "Email không được để trống");
        }

        return result;
    }

}
