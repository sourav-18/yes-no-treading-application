package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Mapper.EventMapper;
import com.ms.yes_no_treading_application.dtos.EventCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.EventDto;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.repositories.EventRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDto create(EventCreateRequestDto eventCreateRequestDto){
        EventEntity newEvent=eventRepository.save(EventMapper.toEntity(eventCreateRequestDto));
        return EventMapper.toDto(newEvent);
    }
}
