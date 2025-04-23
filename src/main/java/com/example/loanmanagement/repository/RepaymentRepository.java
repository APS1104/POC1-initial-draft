package com.example.loanmanagement.repository;

import com.example.loanmanagement.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    Optional<Repayment> findByLoanIdAndLoanUserEmail(Long loanId, String userEmail);
}
