package com.example.loanmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Double balance;
    private Double interestRate;
    private Integer termInMonths; // clarified name
    private LocalDate startDate;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    public enum Status {
        PENDING, APPROVED, REJECTED, COMPLETED, PAID // Added PAID
    }
}
