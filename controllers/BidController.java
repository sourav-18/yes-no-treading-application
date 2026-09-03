package com.ms.yes_no_treading_application.controllers;

import com.ms.yes_no_treading_application.dtos.ApiResponseDto;
import com.ms.yes_no_treading_application.dtos.BidCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.BidDto;
import com.ms.yes_no_treading_application.dtos.EventDto;
import com.ms.yes_no_treading_application.services.BidService;
import com.ms.yes_no_treading_application.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<BidDto>> create(@Valid @RequestBody BidCreateRequestDto bidCreateRequestDto){
       BidDto bidDto= bidService.create(bidCreateRequestDto,1l);
        ApiResponseDto<BidDto> apiResponse =
                new ApiResponseDto<>(HttpStatus.CREATED.value(), "Event created successfully",bidDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
