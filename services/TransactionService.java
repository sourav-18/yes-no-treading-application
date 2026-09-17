package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Repository.TransactionRepository;
import com.ms.yes_no_treading_application.entities.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionEntity createHistory(TransactionEntity transaction){
       return transactionRepository.save(transaction);
    }
}
