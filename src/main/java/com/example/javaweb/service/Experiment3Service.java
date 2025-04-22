package com.example.javaweb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Experiment3Service {

    public Map<String, Object> calculate(String num1Str, String num2Str, String operator) {
        Map<String, Object> result = new HashMap<>();
        double num1, num2, calcResult;

        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            result.put("error", "请输入有效的数字！");
            System.out.println("Calculate error: " + result);
            return result;
        }

        switch (operator) {
            case "+":
                calcResult = num1 + num2;
                break;
            case "-":
                calcResult = num1 - num2;
                break;
            case "*":
                calcResult = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    result.put("error", "除数不能为 0！");
                    System.out.println("Calculate error: " + result);
                    return result;
                }
                calcResult = num1 / num2;
                break;
            default:
                result.put("error", "请选择有效的运算符（+、-、*、/）！");
                System.out.println("Calculate error: " + result);
                return result;
        }

        result.put("num1", num1);
        result.put("num2", num2);
        result.put("operator", operator);
        result.put("calcResult", calcResult);
        System.out.println("Calculate result: " + result);
        return result;
    }

    public Map<String, Object> registerUser(String name, String password, String phone, String className, String[] hobbies) {
        Map<String, Object> result = new HashMap<>();

        if (name == null || name.trim().isEmpty()) {
            result.put("error", "姓名不能为空！");
            return result;
        }
        if (password == null || password.trim().isEmpty()) {
            result.put("error", "密码不能为空！");
            return result;
        }

        if (phone == null || phone.length() != 11 || !phone.matches("\\d+")) {
            result.put("error", "电话号码必须为 11 位数字！");
            return result;
        }

        result.put("name", name);
        result.put("password", password);
        result.put("phone", phone);
        result.put("className", className != null ? className : "未填写");
        result.put("hobbies", hobbies != null ? String.join(", ", hobbies) : "无");

        return result;
    }

    public Map<String, Object> calculateTrianglePerimeter(String side1Str, String side2Str, String side3Str) {
        Map<String, Object> result = new HashMap<>();
        double side1, side2, side3;

        try {
            side1 = Double.parseDouble(side1Str);
            side2 = Double.parseDouble(side2Str);
            side3 = Double.parseDouble(side3Str);
        } catch (NumberFormatException e) {
            result.put("error", "请输入有效的数字！");
            System.out.println("CalculateTrianglePerimeter error: " + result);
            return result;
        }

        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            result.put("error", "边长必须为正数！");
            System.out.println("CalculateTrianglePerimeter error: " + result);
            return result;
        }

        if (side1 + side2 <= side3 || side2 + side3 <= side1 || side1 + side3 <= side2) {
            result.put("error", "三边无法构成三角形！");
            System.out.println("CalculateTrianglePerimeter error: " + result);
            return result;
        }

        double perimeter = side1 + side2 + side3;
        result.put("side1", side1);
        result.put("side2", side2);
        result.put("side3", side3);
        result.put("perimeter", perimeter);
        System.out.println("CalculateTrianglePerimeter result: " + result);
        return result;
    }

    public Map<String, Object> processNavData(String name, String gender) {
        Map<String, Object> result = new HashMap<>();

        if (name == null || name.trim().isEmpty()) {
            result.put("error", "姓名不能为空！");
            return result;
        }
        if (gender == null || (!gender.equals("男") && !gender.equals("女"))) {
            result.put("error", "请选择性别！");
            return result;
        }

        result.put("name", name);
        result.put("gender", gender);
        return result;
    

    }
}