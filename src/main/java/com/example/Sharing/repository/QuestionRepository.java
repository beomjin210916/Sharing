package com.example.Sharing.repository;

import com.example.Sharing.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface QuestionRepository extends JpaRepository<Question, Long> {


}
