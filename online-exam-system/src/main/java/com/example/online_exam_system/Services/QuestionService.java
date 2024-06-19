package com.example.online_exam_system.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_exam_system.Model.Question;
import com.example.online_exam_system.Repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findAll().stream().filter(q -> q.getExam().getId().equals(examId)).collect(Collectors.toList());
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
}
