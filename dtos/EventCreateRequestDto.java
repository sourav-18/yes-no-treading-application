package com.ms.yes_no_treading_application.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.yes_no_treading_application.dtos.anotations.ValidEnum;
import com.ms.yes_no_treading_application.dtos.anotations.ValidPrice;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Service
public class EventCreateRequestDto {
    @Size(max = 100,min = 2)
    @NotBlank
    private String title;
    @Size(max = 200,min = 10)
    @NotBlank
    private String description;

    @ValidPrice
    @NotNull
    private Double yesPrice;

    @ValidPrice
    @NotNull
    private Double noPrice;

    private EventStatusType status;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventStartDateTime;

}
