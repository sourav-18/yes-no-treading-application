package com.ms.yes_no_treading_application.dtos.DbRes;

import com.ms.yes_no_treading_application.entities.types.OptionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BidStatsDbResDto{
    private Long totalCount;
    private OptionType option;
    private Double price;

    public BidStatsDbResDto(Long totalCount, OptionType option, Double price) {
        this.totalCount = totalCount;
        this.option = option;
        this.price = price;
    }
}
