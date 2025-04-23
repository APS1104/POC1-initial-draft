package com.example.loanmanagement.controller;

import com.example.loanmanagement.dto.LoanResponseDto;
import com.example.loanmanagement.model.Loan;
import com.example.loanmanagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/loans")
    public ResponseEntity<List<LoanResponseDto>> allLoans() {
        return ResponseEntity.ok(adminService.getAllLoans());
    }

    @PutMapping("/loans/{loanId}")
    public ResponseEntity<LoanResponseDto> updateStatus(@PathVariable Long loanId, @RequestParam Loan.Status status) {
        return ResponseEntity.ok(adminService.updateLoanStatus(loanId, status));
    }
}
