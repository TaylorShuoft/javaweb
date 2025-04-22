package com.example.javaweb.controller;

import com.example.javaweb.config.ExperimentConfig;
import com.example.javaweb.model.LoginForm;
import com.example.javaweb.service.Experiment1Service;
import com.example.javaweb.service.Experiment2Service;
import com.example.javaweb.service.Experiment3Service;
import com.example.javaweb.service.Experiment4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ExperimentController {

    private static final List<Map<String, Object>> EXPERIMENTS = ExperimentConfig.EXPERIMENTS;
    private static final Map<Integer, List<Map<String, String>>> QUESTIONS = ExperimentConfig.QUESTIONS;

    @Autowired
    private Experiment1Service experiment1Service;

    @Autowired
    private Experiment2Service experiment2Service;

    @Autowired
    private Experiment3Service experiment3Service;

    @Autowired
    private Experiment4Service experiment4Service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("experiments", EXPERIMENTS);
        return "index";
    }

    @GetMapping("/experiment/{id}")
    public String experiment(@PathVariable int id, Model model) {
        Map<String, Object> experiment = EXPERIMENTS.stream()
                .filter(exp -> (int) exp.get("id") == id)
                .findFirst()
                .orElse(null);

        if (experiment == null) return "redirect:/";

        model.addAttribute("experimentTitle", experiment.get("name"));
        model.addAttribute("experimentId", id);
        model.addAttribute("experiments", EXPERIMENTS);
        model.addAttribute("questions", QUESTIONS.getOrDefault(id, Collections.emptyList()));
        model.addAttribute("activeIndex", 0);
        model.addAttribute("priceSum", 0.0);
        model.addAttribute("input", "");

        if (id == 1) {
            model.addAttribute("multiplicationTable", experiment1Service.generateMultiplicationTable());
            model.addAttribute("scoreReport", experiment1Service.generateScoreReport());
        }

        if (id == 2) {
            Map<String, Object> gradeData = experiment2Service.evaluateGrade();
            model.addAttribute("randomScore", gradeData.get("randomScore"));
            model.addAttribute("grade", gradeData.get("grade"));

            Map<String, Object> comparisonData = experiment2Service.compareNumbers();
            model.addAttribute("num1", comparisonData.get("num1"));
            model.addAttribute("num2", comparisonData.get("num2"));
            model.addAttribute("comparison", comparisonData.get("comparison"));
        }

        return "experiment";
    }

    @PostMapping("/experiment/{id}/calculatePriceSum")
    public String calculatePriceSum(@PathVariable int id, @RequestParam("input") String input, Model model) {
        Map<String, Object> experiment = EXPERIMENTS.stream()
                .filter(exp -> (int) exp.get("id") == id)
                .findFirst()
                .orElse(null);

        if (experiment == null || id != 2) return "redirect:/";

        double priceSum = experiment2Service.getPriceSum(input);

        model.addAttribute("experimentTitle", experiment.get("name"));
        model.addAttribute("experimentId", id);
        model.addAttribute("experiments", EXPERIMENTS);
        model.addAttribute("questions", QUESTIONS.getOrDefault(id, Collections.emptyList()));
        model.addAttribute("activeIndex", 3);
        model.addAttribute("priceSum", priceSum);
        model.addAttribute("input", input);

        Map<String, Object> gradeData = experiment2Service.evaluateGrade();
        model.addAttribute("randomScore", gradeData.get("randomScore"));
        model.addAttribute("grade", gradeData.get("grade"));

        Map<String, Object> comparisonData = experiment2Service.compareNumbers();
        model.addAttribute("num1", comparisonData.get("num1"));
        model.addAttribute("num2", comparisonData.get("num2"));
        model.addAttribute("comparison", comparisonData.get("comparison"));

        return "experiment";
    }

    @PostMapping("/experiment/{id}/calculate")
    public String calculate(@PathVariable int id,
                            @RequestParam("num1") String num1,
                            @RequestParam("num2") String num2,
                            @RequestParam("operator") String operator,
                            Model model) {
        Map<String, Object> experiment = EXPERIMENTS.stream()
                .filter(exp -> (int) exp.get("id") == id)
                .findFirst()
                .orElse(null);

        if (experiment == null || id != 3) return "redirect:/";

        Map<String, Object> calcData = experiment3Service.calculate(num1, num2, operator);

        model.addAttribute("experimentTitle", experiment.get("name"));
        model.addAttribute("experimentId", id);
        model.addAttribute("experiments", EXPERIMENTS);
        model.addAttribute("questions", QUESTIONS.getOrDefault(id, Collections.emptyList()));
        model.addAttribute("activeIndex", 0);
        model.addAttribute("calcData", calcData);

        return "experiment";
    }

    @GetMapping("/experiment/3/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("experimentId", 3);
        return "register";
    }

    @PostMapping("/experiment/3/register")
    public String register(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("phone") String phone,
                           @RequestParam(value = "className", required = false) String className,
                           @RequestParam(value = "hobbies", required = false) String[] hobbies,
                           Model model) {
        Map<String, Object> userData = experiment3Service.registerUser(name, password, phone, className, hobbies);

        model.addAttribute("experimentId", 3);
        model.addAttribute("userData", userData);
        return "result";
    }

    @PostMapping("/experiment/{id}/calculatePerimeter")
    public String calculatePerimeter(@PathVariable int id,
                                     @RequestParam("side1") String side1,
                                     @RequestParam("side2") String side2,
                                     @RequestParam("side3") String side3,
                                     Model model) {
        Map<String, Object> experiment = EXPERIMENTS.stream()
                .filter(exp -> (int) exp.get("id") == id)
                .findFirst()
                .orElse(null);

        if (experiment == null || id != 3) return "redirect:/";

        Map<String, Object> perimeterData = experiment3Service.calculateTrianglePerimeter(side1, side2, side3);

        model.addAttribute("experimentTitle", experiment.get("name"));
        model.addAttribute("experimentId", id);
        model.addAttribute("experiments", EXPERIMENTS);
        model.addAttribute("questions", QUESTIONS.getOrDefault(id, Collections.emptyList()));
        model.addAttribute("activeIndex", 2);
        model.addAttribute("perimeterData", perimeterData);

        return "experiment";
    }

    @GetMapping("/experiment/3/nav-test")
    public String showNavTest(Model model) {
        return "nav_test";
    }

    @PostMapping("/experiment/3/nav-test")
    public String processNavTest(@RequestParam("name") String name,
                                 @RequestParam("gender") String gender,
                                 Model model) {
        Map<String, Object> navData = experiment3Service.processNavData(name, gender);
        System.out.println("navData: " + navData);
        model.addAttribute("navData", navData);
        return "nav_test";
    }

    // 实验 4：JavaBean - 题目 1：Java 登陆验证
    @GetMapping("/experiment/4/login-test")
    public String showLoginTest(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("experimentId", 4);
        return "实验四/login_test"; // 修改为新路径
    }

    @PostMapping("/experiment/4/login-test")
    public String processLoginTest(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        Map<String, Object> result = experiment4Service.processLogin(loginForm);
        model.addAttribute("navData", result);
        model.addAttribute("experimentId", 4);
        return "实验四/login_test"; // 修改为新路径
    }

    // 实验 4：JavaBean - 题目 2：Java 统计学生成绩（空方法）
    @GetMapping("/experiment/4/student-scores")
    public String showStudentScores(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/student_scores"; // 修改为新路径
    }

    @PostMapping("/experiment/4/student-scores")
    public String processStudentScores(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/student_scores"; // 修改为新路径
    }

    // 实验 4：JavaBean - 题目 3：Java 三角形判定（空方法）
    @GetMapping("/experiment/4/triangle-judgment")
    public String showTriangleJudgment(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/triangle_judgment"; // 修改为新路径
    }

    @PostMapping("/experiment/4/triangle-judgment")
    public String processTriangleJudgment(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/triangle_judgment"; // 修改为新路径
    }

    // 实验 4：JavaBean - 题目 4：Java 留言板设计（空方法）
    @GetMapping("/experiment/4/message-board")
    public String showMessageBoard(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/message_board"; // 修改为新路径
    }

    @PostMapping("/experiment/4/message-board")
    public String processMessageBoard(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/message_board"; // 修改为新路径
    }

    // 实验 4：JavaBean - 题目 5：Java 计算梯形面积（空方法）
    @GetMapping("/experiment/4/trapezoid-area")
    public String showTrapezoidArea(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/trapezoid_area"; // 修改为新路径
    }

    @PostMapping("/experiment/4/trapezoid-area")
    public String processTrapezoidArea(Model model) {
        model.addAttribute("experimentId", 4);
        return "实验四/trapezoid_area"; // 修改为新路径
    }
}