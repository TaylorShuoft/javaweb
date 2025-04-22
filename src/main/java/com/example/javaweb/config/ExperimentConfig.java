package com.example.javaweb.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExperimentConfig {

    public static final List<Map<String, Object>> EXPERIMENTS = Arrays.asList(
            Map.of("id", 1, "name", "实验 1：Java 基础"),
            Map.of("id", 2, "name", "实验 2：页面标记"),
            Map.of("id", 3, "name", "实验 3：内置对象"),
            Map.of("id", 4, "name", "实验 4：JavaBean")
    );

    public static final Map<Integer, List<Map<String, String>>> QUESTIONS = new HashMap<>();

    static {
        QUESTIONS.put(1, Arrays.asList(
                Map.of("title", "题目 1", "description", "九九乘法表"),
                Map.of("title", "题目 2", "description", "导航栏"),
                Map.of("title", "题目 3", "description", "学生成绩")
        ));
        QUESTIONS.put(2, Arrays.asList(
                Map.of("title", "题目 1", "description", "超链接交换"),
                Map.of("title", "题目 2", "description", "成绩评判"),
                Map.of("title", "题目 3", "description", "比较两数大小"),
                Map.of("title", "题目 4", "description", "消费综合统计")
        ));
        QUESTIONS.put(3, Arrays.asList(
                Map.of("title", "题目 1", "description", "计算器"),
                Map.of("title", "题目 2", "description", "注册表单"),
                Map.of("title", "题目 3", "description", "tag引用"),
                Map.of("title", "题目 4", "description", "模拟导航选页")
        ));
        QUESTIONS.put(4, Arrays.asList(
                Map.of("title", "题目 1", "description", "Java 登陆验证", "endpoint", "login-test"),
                Map.of("title", "题目 2", "description", "Java 统计学生成绩", "endpoint", "student-scores"),
                Map.of("title", "题目 3", "description", "Java 三角形判定", "endpoint", "triangle-judgment"),
                Map.of("title", "题目 4", "description", "Java 留言板设计", "endpoint", "message-board"),
                Map.of("title", "题目 5", "description", "Java 计算梯形面积", "endpoint", "trapezoid-area")
        ));
    }
}