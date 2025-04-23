package com.example.loanmanagement.dto;

import com.example.loanmanagement.model.Repayment;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class RepaymentResponseDto {
    private Long id;
    private Double amount;
    private LocalDate date;

    public static RepaymentResponseDto fromEntity(Repayment r) {
        return new RepaymentResponseDto(r.getId(), r.getAmount(), r.getDate());
    }
}
