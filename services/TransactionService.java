package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void create(){

    }
}
