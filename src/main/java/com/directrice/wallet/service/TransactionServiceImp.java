package com.directrice.wallet.service;

import com.directrice.wallet.entity.Transaction;
import com.directrice.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<Transaction> getUserWalletTransactions(String WalletId) {
     List<Transaction> transactionList=transactionRepository.findByWalletId(WalletId);

        return transactionList;
    }
}
