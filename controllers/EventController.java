package com.ms.yes_no_treading_application.controllers;

import com.ms.yes_no_treading_application.dtos.*;
import com.ms.yes_no_treading_application.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<EventDto>> create(@Valid @RequestBody EventCreateRequestDto body){
        EventDto eventDto=eventService.create(body);
        ApiResponseDto<EventDto> apiResponse =
                new ApiResponseDto<>(HttpStatus.CREATED.value(), "Event created successfully",eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

//    @PostMapping
//    public ResponseEntity<EventCreateRequestDto> create(@Valid @RequestBody EventCreateRequestDto body){
//        return ResponseEntity.status(HttpStatus.CREATED).body(body);
//    }
}
