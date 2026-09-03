package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.mapper.EventMapper;
import com.ms.yes_no_treading_application.dtos.EventCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.EventDto;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.exceptions.InvalidEventPriceException;
import com.ms.yes_no_treading_application.Repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDto create(EventCreateRequestDto eventCreateRequestDto){
        double totalPrice=eventCreateRequestDto.getYesPrice()+eventCreateRequestDto.getNoPrice();
        if(totalPrice!=10){
            throw new InvalidEventPriceException(eventCreateRequestDto.getYesPrice(),eventCreateRequestDto.getNoPrice());
        }
        EventEntity newEvent=eventRepository.save(EventMapper.toEntity(eventCreateRequestDto));
        return EventMapper.toDto(newEvent);
    }
}
