package com.ms.yes_no_treading_application.Repository;

import com.ms.yes_no_treading_application.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}
