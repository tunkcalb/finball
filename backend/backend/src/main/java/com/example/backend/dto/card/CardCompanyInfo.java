package com.example.backend.dto.card;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardCompanyInfo {

    String name;
    String img;
    Long code;
    boolean isConnected;

}