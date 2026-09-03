package com.ms.yes_no_treading_application.mapper;


import com.ms.yes_no_treading_application.dtos.EventCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.EventDto;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;

public class EventMapper {
    public static EventDto toDto(EventEntity event){
        return EventDto.builder().title(event.getTitle())
                .id(event.getId())
                .description(event.getDescription())
                .yesPrice(event.getYesPrice())
                .noPrice(event.getNoPrice())
                .status(event.getStatus())
                .eventStartDateTime(event.getEventStartDateTime())
                .winner(event.getWinner())
                .build();
    }

    public static EventEntity toEntity(EventCreateRequestDto eventCreateRequestDto){
       return EventEntity.builder().title(eventCreateRequestDto.getTitle())
                .description(eventCreateRequestDto.getDescription())
                .yesPrice(eventCreateRequestDto.getYesPrice())
                .noPrice(eventCreateRequestDto.getNoPrice())
                .status(eventCreateRequestDto.getStatus()==null? EventStatusType.upcoming:eventCreateRequestDto.getStatus())
                .eventStartDateTime(eventCreateRequestDto.getEventStartDateTime())
                .build();
    }
}
