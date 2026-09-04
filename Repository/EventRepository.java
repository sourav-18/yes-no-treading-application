package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.types.EventStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {
    List<EventEntity>findByStatus(EventStatusType status);
}
