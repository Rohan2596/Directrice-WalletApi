package com.directrice.wallet.repository;

import com.directrice.wallet.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {

    List<Transaction> findByWalletId(String walletId);

}
