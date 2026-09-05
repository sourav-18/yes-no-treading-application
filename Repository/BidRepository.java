package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.dtos.DbRes.BidStatsDbResDto;
import com.ms.yes_no_treading_application.dtos.LatestBidEventDto;
import com.ms.yes_no_treading_application.entities.BidEntity;
import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<BidEntity,Long> {

    @Query("""
            SELECT new com.ms.yes_no_treading_application.dtos.LatestBidEventDto
            ( b.eventEntity.id eventId,b.eventEntity.title eventTitle, COUNT(b.id) totalBid, SUM(b.price) totalPrice )
            FROM BidEntity b WHERE b.userEntity.id=:userId
            GROUP BY b.eventEntity.id, b.eventEntity.title
            ORDER BY MAX(b.id) DESC
            """)
    List<LatestBidEventDto> getLatestBidEventIds(@Param("userId") Long userId, Pageable pageable);

    @Query("""
            SELECT new com.ms.yes_no_treading_application.dtos.DbRes.BidStatsDbResDto
            ( COUNT (b.option) AS totalCount, b.option AS option, b.price AS price )
            FROM BidEntity b WHERE b.status =:status and b.eventEntity.id = :eventId GROUP BY b.option, b.price
            """)
    List<BidStatsDbResDto> getBidStats(@Param("eventId") Long eventId, @Param("status")BidStatusType status);


//    @Query("""
//            SELECT b.id, b.UserEntity.id, b.price,bid_group_id
//            FROM BidEntity b WHERE status = :status and event_id = ? and choose_option_id = ? and amount = ? and id > ? ORDER BY id ASC LIMIT ?
//            """)
//    List<Object> bidsMatch();
}
