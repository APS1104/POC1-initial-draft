package com.example.loanmanagement.service;

import com.example.loanmanagement.dto.LoanRequestDto;
import com.example.loanmanagement.dto.LoanResponseDto;
import com.example.loanmanagement.model.Loan;
import com.example.loanmanagement.model.User;
import com.example.loanmanagement.repository.LoanRepository;
import com.example.loanmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepo;
    private final UserRepository userRepo;

    public LoanResponseDto applyForLoan(String userEmail, LoanRequestDto dto) {
        User user = userRepo.findByEmail(userEmail).orElseThrow();
        Loan loan = Loan.builder()
                .amount(dto.getAmount())
                .balance(dto.getAmount())
                .interestRate(dto.getInterestRate())
                .termInMonths(dto.getTermInMonths())
                .status(Loan.Status.PENDING)
                .startDate(LocalDate.now())
                .dueDate(LocalDate.now().plusMonths(dto.getTermInMonths()))
                .user(user)
                .build();
        return LoanResponseDto.fromLoan(loanRepo.save(loan));
    }

    public List<LoanResponseDto> getUserLoans(String userEmail) {
        return loanRepo.findByUserEmail(userEmail)
                .stream()
                .map(LoanResponseDto::fromLoan)
                .toList();
    }
}
