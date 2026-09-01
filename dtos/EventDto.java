package com.ms.yes_no_treading_application.dtos;

import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventDto {
    private Long id;
    private String title;
    private String description;
    private float yesPrice;
    private float noPrice;
    private EventStatusType status;
    private LocalDateTime eventStartDateTime;
    private OptionType winner;
}
