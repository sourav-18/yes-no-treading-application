package com.ms.yes_no_treading_application.dtos.DbRes;

import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import lombok.Getter;

@Getter
public class BidMatchDbResDto {
    private Long id;
    private Long userId;

    public BidMatchDbResDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
}
