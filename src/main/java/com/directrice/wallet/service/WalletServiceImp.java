package com.directrice.wallet.service;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.dto.UserSummary;
import com.directrice.wallet.dto.WalletSummary;
import com.directrice.wallet.entity.Transaction;
import com.directrice.wallet.entity.Wallet;
import com.directrice.wallet.enumeration.WalletStatus;
import com.directrice.wallet.exception.WalletException;
import com.directrice.wallet.repository.TransactionRepository;
import com.directrice.wallet.repository.WalletRepository;
import com.directrice.wallet.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.util.Collections;

@Service
public class WalletServiceImp implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;



    private String getUserId(String token){
        RestTemplate restTemplate=new RestTemplate();
        UserSummary userSummary=restTemplate.getForObject("http://localhost:8081/directrice/user/userId?token=" +token,UserSummary.class);
        return userSummary.getUserId();
    }

    private Transaction saveTransaction(Wallet wallet,float amount){
            Transaction transaction=new Transaction();
            transaction.setAmount(amount);
            transaction.setWalletId(wallet.getWalletId());
            transaction.setUserId(wallet.getUserId());
            transaction.setTransactionMessage(wallet.getTransactionMessage());
        return transactionRepository.save(transaction);
    }



    @Override
    public WalletSummary createWallet(String token) {
        String userId=getUserId(token);
        Wallet wallet=new Wallet(userId);
        walletRepository.save(wallet);
        saveTransaction(wallet,wallet.getAmount());
        WalletSummary walletSummary=new WalletSummary(wallet);
        return walletSummary;

    }


    private void amountBalance(Wallet wallet,String transactionMessage,Float amount){
        if(transactionMessage.equals("SEND")){
            if(wallet.getAmount()<amount){
                throw  new WalletException(WalletException.ExceptionTypes.INSUFFICIENT_BALANCE);
            }
        }
        if(transactionMessage.equals("REQUEST")){
            wallet.setAmount(wallet.getAmount() + amount);
        }
        if(transactionMessage.equals("TOPUP")){
            wallet.setAmount(wallet.getAmount()+amount);
        }
    }

    @Override
    public WalletSummary updateWallet(String token,EditWallet editWallet) {
     Wallet wallet=walletRepository.findById(editWallet.getWalletId())
                .orElseThrow(()->new WalletException(WalletException.ExceptionTypes.INVALID_WALLET_ID));

     amountBalance(wallet,editWallet.getTransactionStatus(),editWallet.getAmount());
     wallet.setWalletStatus(WalletStatus.UPDATED.name());
     wallet.setTransactionMessage(editWallet.getTransactionStatus());
     walletRepository.save(wallet);
     saveTransaction(wallet,editWallet.getAmount());
     WalletSummary walletSummary=new WalletSummary(wallet);
     return walletSummary;
    }

    @Override
    public WalletSummary getWallet(String token,String walletId) {
        Wallet wallet= walletRepository.findById(walletId)
                .orElseThrow(()-> new WalletException(WalletException.ExceptionTypes.INVALID_WALLET_ID));
        WalletSummary walletSummary=new WalletSummary(wallet);
        return walletSummary;
    }
}
