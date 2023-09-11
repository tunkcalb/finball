package com.example.backend.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quiz {
    private Long id;
    private String content;
}
