package com.example.backend.controller;

import com.example.backend.dto.quiz.QuizDto;
import com.example.backend.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz")
    public QuizDto.Response getQuizzes(){
        System.out.println("요청들어옴");
        return quizService.getQuizzes();
    }
}
