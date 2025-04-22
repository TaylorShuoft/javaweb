package com.example.javaweb.service;

import com.example.javaweb.model.User;
import com.example.javaweb.model.Student;
import com.example.javaweb.model.Triangle;
import com.example.javaweb.model.Message;
import com.example.javaweb.model.Compute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Experiment4Service {

    private static final Map<String, String> USER_DATABASE = new HashMap<>();
    private static List<Message> messages = new ArrayList<>();
    private static int idCounter = 1;

    static {
        USER_DATABASE.put("zhangsan", "2021001");
    }

    // 案例 1：用户登录验证
    public Map<String, Object> processLogin(User user) {
        Map<String, Object> result = new HashMap<>();

        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null || username.trim().isEmpty()) {
            result.put("error", "用户名不能为空！");
            return result;
        }
        if (password == null || password.trim().isEmpty()) {
            result.put("error", "密码不能为空！");
            return result;
        }

        if (!USER_DATABASE.containsKey(username)) {
            result.put("error", "用户名不存在！");
            return result;
        }
        if (!USER_DATABASE.get(username).equals(password)) {
            result.put("error", "密码错误！");
            return result;
        }

        result.put("username", username);
        result.put("message", "欢迎，" + username + "！");
        return result;
    }

    // 案例 2：统计学生成绩
    public Map<String, Object> processStudentScores(Student student) {
        student.calculateTotal();
        Map<String, Object> result = new HashMap<>();
        result.put("student", student);
        return result;
    }

    // 案例 3：三角形判断
    public Map<String, Object> processTriangleJudgment(Triangle triangle) {
        triangle.checkTriangle();
        Map<String, Object> result = new HashMap<>();
        result.put("triangle", triangle);
        return result;
    }

    // 案例 4：留言板
    public Map<String, Object> processMessageBoard(Message message) {
        message.setId(idCounter++);
        messages.add(message);
        Map<String, Object> result = new HashMap<>();
        result.put("message", message);
        return result;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Map<String, Object> deleteMessage(int id, String adminPassword) {
        Map<String, Object> result = new HashMap<>();
        if (!"admin123".equals(adminPassword)) {
            result.put("error", "管理员密码错误！");
            return result;
        }
        boolean deleted = messages.removeIf(msg -> msg.getId() == id);
        if (deleted) {
            result.put("message", "删除成功！");
        } else {
            result.put("error", "留言ID不存在！");
        }
        return result;
    }

    // 案例 5：计算梯形面积
    public Map<String, Object> processTrapezoidArea(Compute compute) {
        compute.calculateArea();
        Map<String, Object> result = new HashMap<>();
        result.put("compute", compute);
        return result;
    }
}