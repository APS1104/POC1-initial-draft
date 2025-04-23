package com.example.loanmanagement.dto;

import com.example.loanmanagement.model.Loan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanResponseDto {
    private Long id;
    private Double amount;
    private Double balance;
    private Double interestRate;
    private Integer termInMonths;
    private LocalDate dueDate;
    private String status;

    public static LoanResponseDto fromLoan(Loan loan) {
        return LoanResponseDto.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .balance(loan.getBalance())
                .interestRate(loan.getInterestRate())
                .termInMonths(loan.getTermInMonths())
                .dueDate(loan.getDueDate())
                .status(loan.getStatus().name())
                .build();
    }
}
