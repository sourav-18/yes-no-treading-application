package com.ms.yes_no_treading_application.entities;

import com.ms.yes_no_treading_application.entities.types.TradeStatusType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trades")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeEntity extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "yes_bid_id",nullable = false)
    private BidEntity yesBid;

    @OneToOne
    @JoinColumn(name = "no_bid_id",nullable = false)
    private BidEntity noBid;

    @ManyToOne
    @JoinColumn(name = "event_id",nullable = false)
    private EventEntity event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeStatusType status;

}
