package com.example.javaweb.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Experiment1Service {

    public String generateMultiplicationTable() {
        StringBuilder multiplicationTable = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                multiplicationTable.append(j).append("×").append(i).append("=").append(i * j).append(" ");
            }
            multiplicationTable.append("\n");
        }
        return multiplicationTable.toString();
    }

    public List<Map<String, Object>> generateScoreReport() {
        List<Map<String, Object>> scoreReport = new ArrayList<>();
        scoreReport.add(Map.of("name", "张三", "math", 98, "chinese", 90, "english", 110, "total", 298));
        scoreReport.add(Map.of("name", "李四", "math", 70, "chinese", 120, "english", 305, "total", 305));
        scoreReport.add(Map.of("name", "王五", "math", 100, "chinese", 98, "english", 286, "total", 286));
        return scoreReport;
    }
}