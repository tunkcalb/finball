package com.example.backend.dto.transfer;

import com.example.backend.entity.FinBallAccount;
import com.example.backend.entity.FinBallHistory;
import com.example.backend.type.DealType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinBallTradeHistoryDto {
    private String accountNumber;
    private Long value;
    private LocalDate date;
    private LocalTime time;
    private DealType type;
    private Long remain;
    private OppositeBankDto oppositeBankDto;

    public FinBallHistory toFinBallHistory(FinBallAccount finBallAccount) {
        return FinBallHistory.builder()
                .value(this.value)
                .balance(this.remain)
                .date(LocalDateTime.now())
                .dealType(this.type)
                .target(oppositeBankDto.getTarget())
                .opAccount(oppositeBankDto.getAccount())
                .opBankName(oppositeBankDto.getBankName())
                .finBallAccount(finBallAccount)
                .build();
    }
}