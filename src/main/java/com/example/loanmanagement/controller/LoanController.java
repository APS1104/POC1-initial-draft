package com.example.loanmanagement.controller;

import com.example.loanmanagement.dto.*;
import com.example.loanmanagement.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<LoanResponseDto> apply(@AuthenticationPrincipal UserDetails user, @RequestBody LoanRequestDto dto) {
        return ResponseEntity.ok(loanService.applyForLoan(user.getUsername(), dto));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> getUserLoans(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(loanService.getUserLoans(user.getUsername()));
    }
}
