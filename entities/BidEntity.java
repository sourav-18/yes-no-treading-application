package com.ms.yes_no_treading_application.entities;

import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "bids")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidEntity extends BaseEntity{

    @Column(nullable = false, columnDefinition="Decimal(3,2)")
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionType option;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "event_id",nullable = false)
    private EventEntity eventEntity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BidStatusType status;

    @Column(nullable = false)
    private String groupId;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;
}
