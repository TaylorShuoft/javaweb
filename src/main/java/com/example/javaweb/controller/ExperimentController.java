package com.example.javaweb.controller;

import com.example.javaweb.config.ExperimentConfig;
import com.example.javaweb.model.User;
import com.example.javaweb.model.Student;
import com.example.javaweb.model.Triangle;
import com.example.javaweb.model.Message;
import com.example.javaweb.model.Compute;
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

    // 实验 4：JavaBean - 案例 1：用户登录验证
    @GetMapping("/experiment/4/test401-login")
    public String showTest401Login(Model model) {
        model.addAttribute("user", new User());
        return "实验四/test401_login";
    }

    @PostMapping("/experiment/4/test401-login")
    public String processTest401Login(@ModelAttribute("user") User user, Model model) {
        Map<String, Object> result = experiment4Service.processLogin(user);
        model.addAttribute("result", result);
        model.addAttribute("user", user);
        return "实验四/test401_login";
    }

    @GetMapping("/experiment/4/test401-ok")
    public String showTest401Ok(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "实验四/test401_ok";
    }

    @GetMapping("/experiment/4/test401-error")
    public String showTest401Error(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "实验四/test401_error";
    }

    // 实验 4：JavaBean - 案例 2：统计学生成绩
    @GetMapping("/experiment/4/test402-grade")
    public String showTest402Grade(Model model) {
        model.addAttribute("student", new Student());
        return "实验四/test402_grade";
    }

    @PostMapping("/experiment/4/test402-grade")
    public String processTest402Grade(@ModelAttribute("student") Student student, Model model) {
        Map<String, Object> result = experiment4Service.processStudentScores(student);
        model.addAttribute("result", result);
        model.addAttribute("student", student);
        return "实验四/test402_grade";
    }

    // 实验 4：JavaBean - 案例 3：三角形判断
    @GetMapping("/experiment/4/test403-input")
    public String showTest403Input(Model model) {
        model.addAttribute("triangle", new Triangle());
        return "实验四/test403_input";
    }

    @PostMapping("/experiment/4/test403-input")
    public String processTest403Input(@ModelAttribute("triangle") Triangle triangle, Model model) {
        Map<String, Object> result = experiment4Service.processTriangleJudgment(triangle);
        model.addAttribute("result", result);
        model.addAttribute("triangle", triangle);
        return "实验四/test403_input";
    }

    // 实验 4：JavaBean - 案例 4：留言板
    @GetMapping("/experiment/4/test404")
    public String showTest404(Model model) {
        model.addAttribute("message", new Message());
        return "实验四/test404";
    }

    @PostMapping("/experiment/4/test404")
    public String processTest404(@ModelAttribute("message") Message message, Model model) {
        Map<String, Object> result = experiment4Service.processMessageBoard(message);
        model.addAttribute("result", result);
        return "实验四/test404a";
    }

    @GetMapping("/experiment/4/test404b")
    public String showTest404b(Model model) {
        model.addAttribute("messages", experiment4Service.getMessages());
        return "实验四/test404b";
    }

    @GetMapping("/experiment/4/test404c")
    public String showTest404c(Model model) {
        return "实验四/test404c";
    }

    @PostMapping("/experiment/4/test404c")
    public String processTest404c(@RequestParam("id") int id,
                                  @RequestParam("adminPassword") String adminPassword,
                                  Model model) {
        Map<String, Object> result = experiment4Service.deleteMessage(id, adminPassword);
        model.addAttribute("result", result);
        return "实验四/test404c";
    }

    // 实验 4：JavaBean - 案例 5：计算梯形面积
    @GetMapping("/experiment/4/test405a")
    public String showTest405a(Model model) {
        model.addAttribute("compute", new Compute());
        return "实验四/test405a";
    }

    @PostMapping("/experiment/4/test405a")
    public String processTest405a(@ModelAttribute("compute") Compute compute, Model model) {
        Map<String, Object> result = experiment4Service.processTrapezoidArea(compute);
        model.addAttribute("result", result);
        model.addAttribute("compute", compute);
        return "实验四/test405a";
    }
}