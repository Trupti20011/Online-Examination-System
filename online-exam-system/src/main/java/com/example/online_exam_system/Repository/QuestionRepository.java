package com.example.online_exam_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_exam_system.Model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExamId(Long examId);
}
