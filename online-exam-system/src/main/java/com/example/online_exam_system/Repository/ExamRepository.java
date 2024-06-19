package com.example.online_exam_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online_exam_system.Model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}