package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.entities.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreadRepository extends JpaRepository<TradeEntity,Long> {
}
