package com.example.online_exam_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.online_exam_system.Services.ExamService;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;


@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/exams")
    public String getAllExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "exams";
    }

    @GetMapping("/exams/{id}")
    public String getExam(@PathVariable Long id, Model model) {
        model.addAttribute("exam", examService.getExamById(id));
        return "exam";
    }
}
