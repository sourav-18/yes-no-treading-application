package com.ms.yes_no_treading_application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "yes_price",nullable = false, columnDefinition="Decimal(10,2)")
    private Double yesPrice;

    @Column(name = "no_price",nullable = false, columnDefinition="Decimal(10,2)")
    private Double noPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatusType status=EventStatusType.upcoming;


    @Column(name = "event_start_date_time")
    private LocalDateTime eventStartDateTime;

    @Enumerated(EnumType.STRING)
    private OptionType winner;

    @OneToMany(mappedBy = "eventEntity")
    @JsonIgnore
    List<BidEntity> bids;

    //todo -> createdBy , updatedBy
}
