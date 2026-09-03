package com.ms.yes_no_treading_application.dtos;

import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BidDto {
    private Long id;
    private Double price;
    private Long userId;
    private Long eventId;
    private OptionType option;
    private BidStatusType status;
    private LocalDateTime createdAt;
}
