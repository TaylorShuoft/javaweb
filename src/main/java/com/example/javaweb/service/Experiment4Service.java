package com.example.javaweb.service;



import com.example.javaweb.model.LoginForm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Experiment4Service {

    // 模拟一个用户数据库，实际项目中应使用数据库
    private static final Map<String, String> USER_DATABASE = new HashMap<>();

    static {
        USER_DATABASE.put("admin", "123456"); // 用户名: admin, 密码: 123456
        USER_DATABASE.put("user", "password"); // 用户名: user, 密码: password
    }

    // 题目 1：Java 登陆验证
    public Map<String, Object> processLogin(LoginForm loginForm) {
        Map<String, Object> result = new HashMap<>();

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        // 验证用户名和密码是否为空
        if (username == null || username.trim().isEmpty()) {
            result.put("error", "用户名不能为空！");
            return result;
        }
        if (password == null || password.trim().isEmpty()) {
            result.put("error", "密码不能为空！");
            return result;
        }

        // 验证用户名和密码是否匹配
        if (!USER_DATABASE.containsKey(username)) {
            result.put("error", "用户名不存在！");
            return result;
        }
        if (!USER_DATABASE.get(username).equals(password)) {
            result.put("error", "密码错误！");
            return result;
        }

        // 验证通过
        result.put("username", username);
        result.put("message", "欢迎，" + username + "！");
        return result;
    }

    // 题目 2：Java 统计学生成绩（空方法）
    public Map<String, Object> processStudentScores() {
        return new HashMap<>();
    }

    // 题目 3：Java 三角形判定（空方法）
    public Map<String, Object> processTriangleJudgment() {
        return new HashMap<>();
    }

    // 题目 4：Java 留言板设计（空方法）
    public Map<String, Object> processMessageBoard() {
        return new HashMap<>();
    }

    // 题目 5：Java 计算梯形面积（空方法）
    public Map<String, Object> processTrapezoidArea() {
        return new HashMap<>();
    }
}