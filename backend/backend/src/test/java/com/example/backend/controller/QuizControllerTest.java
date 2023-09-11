package com.example.backend.controller;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.backend.service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    QuizService quizService;

    @Test
    void getQuizzes() throws Exception{

        final ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/quiz"));


        actions
                .andDo(print());
    }
}