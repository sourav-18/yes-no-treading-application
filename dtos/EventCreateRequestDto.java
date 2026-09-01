package com.ms.yes_no_treading_application.dtos;


import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Service
public class EventCreateRequestDto {
    @Size(max = 100,min = 2)
    @NotBlank
    private String title;
    @Size(max = 200,min = 2)
    @NotBlank
    private String description;
    private float yesPrice;
    private float noPrice;
    private EventStatusType status;
    private LocalDateTime eventStartDateTime;

}
