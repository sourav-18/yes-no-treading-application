package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Repository.BidRepository;
import com.ms.yes_no_treading_application.Repository.EventRepository;
import com.ms.yes_no_treading_application.Repository.TreadRepository;
import com.ms.yes_no_treading_application.dtos.DbRes.BidMatchDbResDto;
import com.ms.yes_no_treading_application.dtos.DbRes.BidStatsDbResDto;
import com.ms.yes_no_treading_application.entities.BidEntity;
import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.TradeEntity;
import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class MatchingService {
    private final BidRepository bidRepository;
    private final EventRepository eventRepository;
    private final TreadRepository treadRepository;

    public void matchHandler() {
        List<EventEntity> events = eventRepository.findByStatus(EventStatusType.live);
        if (events.isEmpty()) return;

        for (EventEntity event : events) {
            List<BidStatsDbResDto> bidStats = bidRepository.getBidStats(event.getId(), BidStatusType.ideal);
            if (bidStats.isEmpty()) continue;
            matchBidsHandler(bidStats, event.getId());
        }
    }

    private void matchBidsHandler(List<BidStatsDbResDto> bidStats, Long eventId) {
        List<BidStatsDbResDto> yesBidStats = new ArrayList<>();
        List<BidStatsDbResDto> noBidStats = new ArrayList<>();

        for (BidStatsDbResDto stats : bidStats) {
            if (stats.getOption() == OptionType.YES) {
                yesBidStats.add(stats);
            } else if (stats.getOption() == OptionType.NO) {
                noBidStats.add(stats);
            }
        }

        if (yesBidStats.isEmpty() || noBidStats.isEmpty()) return;

        yesBidStats.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        noBidStats.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));

        List<MatchBidInfo> exactMatch = new ArrayList<>();
        List<MatchBidInfo> highMatch = new ArrayList<>();

        //exact match
        int yestStart = 0, noStart = noBidStats.size() - 1;
        while (yestStart < yesBidStats.size() && noStart >= 0) {
            BidStatsDbResDto yes = yesBidStats.get(yestStart);
            BidStatsDbResDto no = noBidStats.get(noStart);

            if (yes.getTotalCount().equals(0L)) {
                yestStart++;
                continue;
            }
            if (no.getTotalCount().equals(0L)) {
                noStart--;
                continue;
            }

            if (yes.getPrice() + no.getPrice() == 10) {
                long minBidCount = Math.min(yes.getTotalCount(), no.getTotalCount());
                yes.setTotalCount(yes.getTotalCount() - minBidCount);
                no.setTotalCount(no.getTotalCount() - minBidCount);

                MatchBidInfo bidInfo = MatchBidInfo.builder()
                        .eventId(eventId)
                        .bidCount(minBidCount)
                        .yesPrice(yes.getPrice())
                        .noPrice(no.getPrice())
                        .build();
                exactMatch.add(bidInfo);
                yestStart++;
                noStart--;

            } else if (yes.getPrice() + no.getPrice() > 10) {
                noStart--;
            } else {
                yestStart++;
            }
        }

        //over 10 bids match
        yestStart = 0;
        noStart = noBidStats.size() - 1;

        while (yestStart < yesBidStats.size() && noStart >= 0) {
            BidStatsDbResDto yes = yesBidStats.get(yestStart);
            BidStatsDbResDto no = noBidStats.get(noStart);
            if (yes.getTotalCount().equals(0L)) {
                yestStart++;
                continue;
            }
            if (no.getTotalCount().equals(0L)) {
                noStart--;
                continue;
            }
            if (yes.getPrice() + no.getPrice() > 10) {
                long minBidCount = Math.min(yes.getTotalCount(), no.getTotalCount());
                yes.setTotalCount(yes.getTotalCount() - minBidCount);
                no.setTotalCount(no.getTotalCount() - minBidCount);

                MatchBidInfo bidInfo = MatchBidInfo.builder()
                        .eventId(eventId)
                        .bidCount(minBidCount)
                        .yesPrice(yes.getPrice())
                        .noPrice(no.getPrice())
                        .build();
                highMatch.add(bidInfo);
            } else {
                yestStart++;
            }
        }
    }

    private void exactMatchHandler(List<MatchBidInfo> matchBidInfos) {
        for (MatchBidInfo match : matchBidInfos) {
            int BATCH_SIZE = 500;
            long bidCount = match.getBidCount();
            long yesCursor = 0;
            long noCursor = 0;

            while (bidCount > 0) {
                int limit = (int) Math.min(BATCH_SIZE, bidCount);
                bidCount -= limit;
                exactMatchRoundHandler(match, limit, yesCursor, noCursor);
            }
        }
    }

    private void exactMatchRoundHandler(MatchBidInfo match, int limit, long yesCursor, long
            noCursor) {
        List<BidMatchDbResDto> yesBidMatch = bidRepository.getIdealBidMatch(match.getEventId(), OptionType.YES, match.getNoPrice(), noCursor);
        List<BidMatchDbResDto> noBidMatch = bidRepository.getIdealBidMatch(match.getEventId(), OptionType.NO, match.getYesPrice(), yesCursor);

        for (int i = 0; i < limit; i++) {
            if (!Objects.equals(yesBidMatch.get(i).getUserId(), noBidMatch.get(i).getUserId())) {
                exactMatchRoundBidsHandler(yesBidMatch.get(i).getId(),noBidMatch.get(i).getId(),match.getEventId());
            }
        }

    }


    @Transactional
    private void exactMatchRoundBidsHandler(long yesBidId,long noBidId,long eventId) {
        BidEntity yesBidEntity=bidRepository.findByIdWithLock(yesBidId).orElseThrow();
        BidEntity noBidEntity=bidRepository.findByIdWithLock(noBidId).orElseThrow();

        if(yesBidEntity.getPrice()+noBidEntity.getPrice()!=0||
           yesBidEntity.getOption()!=OptionType.YES||
           noBidEntity.getOption()!=OptionType.NO||
           Objects.equals(yesBidEntity.getEventEntity().getId(), noBidEntity.getUserEntity().getId())
        ){
            throw new RuntimeException("Invalid yesBid and noBid in exactMatchRoundBidsHandler");
        }

        TradeEntity newTread=TradeEntity.builder()
                .yesBid(yesBidEntity)
                .noBid(noBidEntity)
                .event(eventRepository.getReferenceById(eventId))
                .build();

        treadRepository.save(newTread);
    }
}




    @Getter
    @Builder
    @ToString
    class MatchBidInfo{
        private final Long eventId;
        private final Double yesPrice;
        private final Double noPrice;
        private final Long bidCount;
    }
