package com.example.loanmanagement.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
    private Double amount;
    private Double interestRate;
    private Integer termInMonths;
}
