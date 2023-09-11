package com.example.backend.service;

import com.example.backend.dto.quiz.Quiz;
import com.example.backend.dto.quiz.QuizDto;
import com.example.backend.dto.quiz.QuizDto.Response;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    public QuizDto.Response getQuizzes() {
        QuizDto.Response response = new Response();
        response.setCode("200");
        response.setMessage("퀴즈 리스트를 테스트로 3개 호출합니다.");

        ArrayList<Quiz> quizzes = new ArrayList<>();

        quizzes.add(new Quiz(1L, "1번 퀴즈입니다."));
        quizzes.add(new Quiz(2L, "2번 퀴즈입니다."));
        quizzes.add(new Quiz(3L, "3번 퀴즈입니다."));

        response.setQuizzes(quizzes);

        return response;
    }
}
