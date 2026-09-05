package com.ms.yes_no_treading_application.mapper;

import com.ms.yes_no_treading_application.dtos.BidCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.BidDto;
import com.ms.yes_no_treading_application.entities.BidEntity;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.entities.types.BidStatusType;

import java.util.UUID;

public class BidMapper {
    public static BidEntity toEntity(BidCreateRequestDto bidCreateRequestDto, EventEntity event, UserEntity user, String groupId){
       return BidEntity.builder()
                .price(bidCreateRequestDto.getPrice())
                .option(bidCreateRequestDto.getOption())
                .status(BidStatusType.ideal)
                .eventEntity(event)
                .userEntity(user)
                .groupId(groupId)
                .build();
    }
    public static BidDto toDto(BidEntity bid){
        return  BidDto.builder()
                .id(bid.getId())
                .price(bid.getPrice())
                .option(bid.getOption())
                .status(bid.getStatus())
                .eventId(bid.getEventEntity().getId())
                .userId(bid.getUserEntity().getId())
                .createdAt(bid.getCreatedAt())
                .build();
    }
}