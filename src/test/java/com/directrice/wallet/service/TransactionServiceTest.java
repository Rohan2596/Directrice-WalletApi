package com.directrice.wallet.service;

import com.directrice.wallet.entity.Transaction;
import com.directrice.wallet.entity.Wallet;
import com.directrice.wallet.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {


    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImp transactionServiceImp;

    private String walletId;
    private Wallet wallet;
    private Transaction transaction;
    private List<Transaction> transactionList=new ArrayList<>();

    @BeforeEach
    void setUp(){
        this.walletId="eyJhbGciOiJIUzI1NiJ9";
        this.wallet=new Wallet("eyJhbGciOiJIUzI1NiJ9");
        this.transaction=new Transaction();
        this.transaction.setAmount(this.wallet.getAmount());
        this.transaction.setWalletId(this.wallet.getWalletId());
        this.transaction.setUserId(this.wallet.getUserId());
        this.transaction.setTransactionMessage(this.wallet.getTransactionMessage());
        this.transactionList.add(this.transaction);

    }

    @Test
    void givenValidTokenAndWalletId_whenAuthenticated_shouldReturnResponse(){
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(transactionRepository.findByWalletId(any())).thenReturn(this.transactionList);
        List<Transaction> newTransactionList=transactionServiceImp.getUserWalletTransactions(this.walletId);
        Assert.assertEquals(1,newTransactionList.size());

    }


}
