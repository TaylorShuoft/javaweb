package com.example.javaweb.model;

public class LoginForm {
    private String username;
    private String password;

    // 构造函数
    public LoginForm() {
    }

    // Getters 和 Setters
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