package com.ms.yes_no_treading_application.mapper;


import com.ms.yes_no_treading_application.entities.EventEntity;
import com.ms.yes_no_treading_application.entities.TransactionEntity;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.entities.types.AccountType;
import com.ms.yes_no_treading_application.entities.types.TransactionActionType;

public class TransactionMapper {
    public static TransactionEntity toEntityForBidCreate(double amount, AccountType accountType, UserEntity user){
       return TransactionEntity.builder()
                .amount(amount)
                .accountType(accountType)
                .action(TransactionActionType.debit)
                .user(user).build();
    }
}
