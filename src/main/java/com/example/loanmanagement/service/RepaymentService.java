package com.example.loanmanagement.service;

import com.example.loanmanagement.dto.RepaymentRequestDto;
import com.example.loanmanagement.dto.RepaymentResponseDto;
import com.example.loanmanagement.model.*;
import com.example.loanmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepaymentService {

    private final LoanRepository loanRepo;
    private final RepaymentRepository repaymentRepo;

    public RepaymentResponseDto repayLoan(String userEmail, RepaymentRequestDto dto) {
        Loan loan = loanRepo.findById(dto.getLoanId())
                .filter(l -> l.getUser().getEmail().equals(userEmail))
                .orElseThrow();

        loan.setBalance(loan.getBalance() - dto.getAmount());
        if (loan.getBalance() <= 0) {
            loan.setBalance(0.0);
            loan.setStatus(Loan.Status.PAID);
        }

        Repayment repayment = Repayment.builder()
                .amount(dto.getAmount())
                .loan(loan)
                .date(LocalDate.now())
                .build();

        loanRepo.save(loan);
        repaymentRepo.save(repayment);

        return RepaymentResponseDto.fromEntity(repayment);
    }

    public List<RepaymentResponseDto> getRepaymentHistory(String userEmail, Long loanId) {
        return repaymentRepo.findByLoanIdAndLoanUserEmail(loanId, userEmail)
                .stream()
                .map(RepaymentResponseDto::fromEntity)
                .toList();
    }
}
