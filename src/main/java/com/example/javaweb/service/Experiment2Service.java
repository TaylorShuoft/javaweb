package com.example.javaweb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap; // 显式导入 HashMap
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Experiment2Service {

    public Map<String, Object> evaluateGrade() {
        int randomScore = (int) (Math.random() * 101); // 0-100 随机成绩
        String grade;
        switch (randomScore / 10) {
            case 10:
            case 9:
                grade = "A";
                break;
            case 8:
                grade = "B";
                break;
            case 7:
                grade = "C";
                break;
            case 6:
                grade = "D";
                break;
            default:
                grade = "不及格";
        }
        Map<String, Object> result = new HashMap<>(); // 使用泛型
        result.put("randomScore", randomScore);
        result.put("grade", grade);
        return result;
    }

    public Map<String, Object> compareNumbers() {
        int num1 = (int) (Math.random() * 100); // 0-99 随机数
        int num2 = (int) (Math.random() * 100); // 0-99 随机数
        String comparison = num1 > num2 ? "num1 大于 num2" : (num1 == num2 ? num1 + "等于" + num2 : num1 + "小于" + num2);
        Map<String, Object> result = new HashMap<>(); // 使用泛型
        result.put("num1", num1);
        result.put("num2", num2);
        result.put("comparison", comparison);
        return result;
    }

    public double getPriceSum(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0.0; // 处理空输入
        }
        Pattern pattern = Pattern.compile("-?[0-9]+[0-9]*[.]?[0-9]*");
        Matcher matcher = pattern.matcher(input);
        double sum = 0;
        while (matcher.find()) {
            try {
                String str = matcher.group();
                sum += Double.parseDouble(str);
            } catch (NumberFormatException e) {
                // 忽略无效数字
                System.err.println("Invalid number format: " + e.getMessage());
            }
        }
        return sum;
    }
}