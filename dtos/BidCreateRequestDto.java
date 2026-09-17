package com.ms.yes_no_treading_application.dtos;

import com.ms.yes_no_treading_application.dtos.anotations.ValidPrice;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidCreateRequestDto {

    @NotNull
    @Positive
    private Long eventId;

    @ValidPrice
    @NotNull
    private Double price;

    @NotNull
    private OptionType option;

    @Min(1)
//    @Max(100)
    @NotNull
    private Integer quantity;
}
