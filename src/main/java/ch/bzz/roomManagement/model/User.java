package ch.bzz.roomManagement.model;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.ws.rs.FormParam;

public class User {

    private Integer userId;
    @FormParam("username")
    private String username;
    @FormParam("password")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
