package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.entities.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<BidEntity,Long> {

}
