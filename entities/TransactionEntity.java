package com.ms.yes_no_treading_application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.yes_no_treading_application.entities.types.AccountType;
import com.ms.yes_no_treading_application.entities.types.TransactionActionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity extends BaseEntity{
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bid_id")
    private BidEntity bid;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type",nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionActionType action;

}
