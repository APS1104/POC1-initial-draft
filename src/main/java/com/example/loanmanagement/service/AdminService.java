package com.example.loanmanagement.service;

import com.example.loanmanagement.dto.LoanResponseDto;
import com.example.loanmanagement.model.Loan;
import com.example.loanmanagement.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final LoanRepository loanRepo;

    public List<LoanResponseDto> getAllLoans() {
        return loanRepo.findAll().stream().map(LoanResponseDto::fromLoan).toList();
    }

    public LoanResponseDto updateLoanStatus(Long loanId, Loan.Status status) {
        Loan loan = loanRepo.findById(loanId).orElseThrow();
        loan.setStatus(status);
        return LoanResponseDto.fromLoan(loanRepo.save(loan));
    }
}
