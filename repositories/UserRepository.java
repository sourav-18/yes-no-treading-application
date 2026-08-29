package com.ms.yes_no_treading_application.repositories;

import com.ms.yes_no_treading_application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, UserEntity> {

}
