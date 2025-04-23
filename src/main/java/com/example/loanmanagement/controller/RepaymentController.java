package com.example.loanmanagement.controller;

import com.example.loanmanagement.dto.*;
import com.example.loanmanagement.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/repayments")
@RequiredArgsConstructor
public class RepaymentController {

    private final RepaymentService repaymentService;

    @PostMapping
    public ResponseEntity<RepaymentResponseDto> repay(@AuthenticationPrincipal UserDetails user, @RequestBody RepaymentRequestDto dto) {
        return ResponseEntity.ok(repaymentService.repayLoan(user.getUsername(), dto));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<List<RepaymentResponseDto>> history(@AuthenticationPrincipal UserDetails user, @PathVariable Long loanId) {
        return ResponseEntity.ok(repaymentService.getRepaymentHistory(user.getUsername(), loanId));
    }
}
