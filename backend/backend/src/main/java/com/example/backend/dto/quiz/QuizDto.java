package com.example.backend.dto.quiz;

import java.util.ArrayList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {
    @Data
    public static class Response{
        private String code;
        private String message;
        private ArrayList<Quiz> quizzes;
    }

}
