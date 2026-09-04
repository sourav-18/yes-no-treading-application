package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Repository.BidRepository;
import com.ms.yes_no_treading_application.Repository.EventRepository;
import com.ms.yes_no_treading_application.dtos.DbRes.BidStatsDbResDto;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class MatchingService {
    private final BidRepository bidRepository;
    private final EventRepository eventRepository;

    public void matchHandler(){
        List<EventEntity> events = eventRepository.findByStatus(EventStatusType.live);
        if(events.isEmpty())return;

        for(EventEntity event:events){
            List<BidStatsDbResDto> bidStats=bidRepository.getBidStats(event.getId(), BidStatusType.ideal);
            if(bidStats.isEmpty())continue;
            matchBidsHandler(bidStats,event.getId());
        }
    }

    private void matchBidsHandler( List<BidStatsDbResDto> bidStats,Long eventId){
        List<BidStatsDbResDto>yesBidStats=new ArrayList<>();
        List<BidStatsDbResDto>noBidStats=new ArrayList<>();

        for(BidStatsDbResDto stats:bidStats){
            if(stats.getOption()==OptionType.YES){
                yesBidStats.add(stats);
            }else if(stats.getOption()== OptionType.NO){
                noBidStats.add(stats);
            }
        }

        if(yesBidStats.isEmpty()||noBidStats.isEmpty())return;

        yesBidStats.sort((a,b)->Double.compare(a.getPrice(),b.getPrice()));
        noBidStats.sort((a,b)->Double.compare(a.getPrice(),b.getPrice()));

        List<MatchBidInfo>exactMatch=new ArrayList<>();

        //exact match
        int yestStart=0,noStart=noBidStats.size()-1;
        while (yestStart<yesBidStats.size()&&noStart>=0){
            BidStatsDbResDto yes=yesBidStats.get(yestStart);
            BidStatsDbResDto no=noBidStats.get(noStart);
            if(yes.getPrice()+no.getPrice()==10){
                long minBidCount=Math.min(yes.getTotalCount(),no.getTotalCount());
                yes.setTotalCount(yes.getTotalCount()-minBidCount);
                no.setTotalCount(no.getTotalCount()-minBidCount);

                MatchBidInfo bidInfo = MatchBidInfo.builder()
                        .eventId(eventId)
                        .bidCount(minBidCount)
                        .yesPrice(yes.getPrice())
                        .noPrice(no.getPrice())
                        .build();
                exactMatch.add(bidInfo);
                yestStart++;
                noStart--;

            } else if (yes.getPrice()+no.getPrice()>10) {
                noStart--;
            }else {
                yestStart++;
            }
        }


    }
}


@Builder
@ToString
class MatchBidInfo{
    private final Long eventId;
    private final Double yesPrice;
    private final Double noPrice;
    private final Long bidCount;
}
