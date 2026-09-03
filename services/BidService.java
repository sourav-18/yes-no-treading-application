package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Repository.BidRepository;
import com.ms.yes_no_treading_application.Repository.EventRepository;
import com.ms.yes_no_treading_application.Repository.UserRepository;
import com.ms.yes_no_treading_application.dtos.BidCreateRequestDto;
import com.ms.yes_no_treading_application.dtos.BidDto;
import com.ms.yes_no_treading_application.entities.BidEntity;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.exceptions.DataNotFoundException;
import com.ms.yes_no_treading_application.mapper.BidMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Transactional
    public BidDto create(BidCreateRequestDto body, Long userId){
        EventEntity event=eventRepository.findById(body.getEventId())
                .orElseThrow(()->new DataNotFoundException("eventId"));

       UserEntity user=userRepository.findByIdWithLock(userId)
               .orElseThrow(()->new DataNotFoundException("userId"));

       if(user.getDepositBalance()+user.getWinBalance()< body.getPrice()){
           throw new RuntimeException("Insufficient balance");
       }

       double deductPrice= body.getPrice();

       if(user.getDepositBalance()>0){
           double tempPrice=Math.min(user.getDepositBalance(),deductPrice);
           deductPrice-=tempPrice;
           user.setDepositBalance(user.getDepositBalance()-tempPrice);
       }

       if(deductPrice>0&&user.getWinBalance()>0){
           double tempPrice=Math.min(user.getWinBalance(),deductPrice);
           deductPrice-=tempPrice;
           user.setWinBalance(user.getWinBalance()-tempPrice);
       }

       if(deductPrice!=0){
           throw new RuntimeException("Internal error");
       }

        BidEntity newBid= bidRepository.save( BidMapper.toEntity(
                body,event,userRepository.getReferenceById(userId)
        ));

       return BidMapper.toDto(newBid);
    }
}
