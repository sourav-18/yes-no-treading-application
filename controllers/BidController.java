package com.ms.yes_no_treading_application.controllers;

import com.ms.yes_no_treading_application.dtos.*;
import com.ms.yes_no_treading_application.services.BidService;
import com.ms.yes_no_treading_application.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<LatestBidEventDto>>> getMyBids(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        List<LatestBidEventDto> latestBidEvents= bidService.myList(1l, page, limit);
        ApiResponseDto<List<LatestBidEventDto>> apiResponse =
                new ApiResponseDto<>(HttpStatus.OK.value(), "Latest bidEvent fetch successfully",latestBidEvents);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
