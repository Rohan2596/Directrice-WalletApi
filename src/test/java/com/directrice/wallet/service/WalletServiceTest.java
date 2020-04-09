package com.directrice.wallet.service;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.dto.WalletSummary;
import com.directrice.wallet.entity.Transaction;
import com.directrice.wallet.entity.Wallet;
import com.directrice.wallet.exception.WalletException;
import com.directrice.wallet.repository.TransactionRepository;
import com.directrice.wallet.repository.WalletRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletServiceTest {


    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private WalletSummary walletSummary;
    private Wallet wallet;
    private Transaction transaction;
    private String token;
    private EditWallet editWallet;

    @InjectMocks
    WalletServiceImp walletServiceImp;

    @BeforeEach
    void setup(){
        this.wallet=new Wallet("eyJhbGciOiJIUzI1NiJ9");
        this.walletSummary=new WalletSummary(this.wallet);
        this.transaction=new Transaction();
        transaction.setAmount(wallet.getAmount());
        transaction.setWalletId(wallet.getWalletId());
        transaction.setUserId(wallet.getUserId());
        transaction.setTransactionMessage(wallet.getTransactionMessage());
        this.token="token";
        this.editWallet=new EditWallet("eyJhbGciOiJIUzI1NiJ9",0,"SEND");



    }

//CREATE WALLET
    @Test
    void givenValidToken_whenAuthenticated_shouldCreateWallet(){

        when(walletRepository.save(any())).thenReturn(wallet);
        when(transactionRepository.save(any())).thenReturn(transaction);
        WalletSummary newWalletSummary=walletServiceImp.createWallet(token);
        Assert.assertEquals("token",newWalletSummary.getUserId());
        Assert.assertEquals("CREATED",newWalletSummary.getTransactionStatus());

    }

    //UPDATE WALLET

    @Test
    void givenValidToken_whenEdited_shouldUpdatedWallet(){

        when(walletRepository.save(any())).thenReturn(wallet);
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(walletRepository.findById(any())).thenReturn(Optional.of(this.wallet));

        WalletSummary newWalletSummary=walletServiceImp.updateWallet(token,this.editWallet);
        Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9",newWalletSummary.getUserId());
        Assert.assertEquals("SEND",newWalletSummary.getTransactionStatus());

    }

    @Test
    void givenValidTokenAndInvalidUserId_whenEdited_shouldUpdatedWallet(){
        try {
            when(walletRepository.save(any())).thenReturn(wallet);
            when(transactionRepository.save(any())).thenReturn(transaction);

            WalletSummary newWalletSummary = walletServiceImp.updateWallet(token, this.editWallet);
        }catch (WalletException walletException){
            Assert.assertEquals("Invalid Wallet Id.",walletException.exceptionTypes.errorMessage);

        }

    }


//Get All Details
    @Test
    void givenValidTokenAndUserId_whenAuthenticated_shouldGiveWallet(){
        when(walletRepository.save(any())).thenReturn(wallet);
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(walletRepository.findById(any())).thenReturn(Optional.of(this.wallet));
        WalletSummary newWalletSummary=walletServiceImp.getWallet(token,wallet.getWalletId());
        Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9",newWalletSummary.getUserId());
        Assert.assertEquals("CREATED",newWalletSummary.getTransactionStatus());

    }


    @Test
    void givenValidTokenAndInValidUserId_whenAuthenticated_shouldGiveWallet(){
      try {
          when(walletRepository.save(any())).thenReturn(wallet);
          when(transactionRepository.save(any())).thenReturn(transaction);
          WalletSummary newWalletSummary = walletServiceImp.getWallet(token, wallet.getWalletId());
      }catch (WalletException walletException){
          Assert.assertEquals("Invalid Wallet Id.",walletException.exceptionTypes.errorMessage);
      }

    }
}
