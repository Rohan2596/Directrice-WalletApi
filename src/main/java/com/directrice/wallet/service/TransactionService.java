package com.directrice.wallet.service;


import com.directrice.wallet.entity.Transaction;

import java.util.List;

public interface TransactionService {


    List<Transaction> getUserWalletTransactions(String WalletId);

}
