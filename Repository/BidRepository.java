package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.dtos.DbRes.BidStatsDbResDto;
import com.ms.yes_no_treading_application.dtos.DbRes.BidMatchDbResDto;
import com.ms.yes_no_treading_application.dtos.LatestBidEventDto;
import com.ms.yes_no_treading_application.entities.BidEntity;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.entities.types.BidStatusType;
import com.ms.yes_no_treading_application.entities.types.OptionType;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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



    @Query("""
            SELECT new com.ms.yes_no_treading_application.dtos.DbRes.BidMatchDbResDto
            ( b.id id, b.userEntity.id userId)
            FROM BidEntity b
            WHERE b.status = :status AND b.eventEntity.id=:eventId AND b.option=:option AND b.price=:price AND b.id>:cursor
            ORDER BY id ASC LIMIT :limit
            """)
    List<BidMatchDbResDto> bidsMatch(
            @Param("status")BidStatusType status,
            @Param("eventId")Long eventId,
            @Param("option") OptionType option,
            @Param("price") Double price,
            @Param("cursor") Long cursor
    );

    default List<BidMatchDbResDto> getIdealBidMatch(Long eventId,OptionType option,Double price,Long cursor){
        return bidsMatch(BidStatusType.ideal,eventId,option,price,cursor);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BidEntity b WHERE b.id=?1")
    Optional<BidEntity> findByIdWithLock(Long id);
}
