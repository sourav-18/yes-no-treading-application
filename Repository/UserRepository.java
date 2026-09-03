package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.entities.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM UserEntity u WHERE u.id=?1")
    Optional<UserEntity> findByIdWithLock(Long id);

//    @Query("UPDATE FROM UserEntity u WHERE u.id=:id AND u.depositBalance+u.winBalance>=:price")
//    Integer debitBalanceForBid(@Param("id") Long id,@Param("price") Double price);
//
//    @Modifying
//    @Transactional
//    @Query("""
//    UPDATE UserEntity u
//    SET u.depositBalance = u.depositBalance - :price
//    WHERE u.id = :id
//      AND u.depositBalance + u.winBalance >= :price
//""")
//    int deductBalance(@Param("id") Long id, @Param("price") Double price);

}
