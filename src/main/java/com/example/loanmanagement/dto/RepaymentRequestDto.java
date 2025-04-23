package com.example.loanmanagement.dto;

import lombok.Data;

@Data
public class RepaymentRequestDto {
    private Long loanId;
    private Double amount;
}
