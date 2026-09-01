package com.ms.yes_no_treading_application.entities;

import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

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

    @Column(name = "yes_price",nullable = false)
    @ColumnDefault("5")
    private float yesPrice= 5.0F;

    @Column(name = "no_price")
    @ColumnDefault("5")
    private float noPrice=5.0F;

    @Enumerated(EnumType.STRING)
//    @ColumnDefault(EventStatusType.upcoming.name())
    @Column(nullable = false)
    private EventStatusType status=EventStatusType.upcoming;


    @Column(name = "event_start_date_time")
    private LocalDateTime eventStartDateTime;

    @Enumerated(EnumType.STRING)
    private OptionType winner;

    //todo -> createdBy , updatedBy
}
