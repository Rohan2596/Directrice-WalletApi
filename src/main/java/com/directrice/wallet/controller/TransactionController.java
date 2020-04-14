package com.directrice.wallet.controller;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.entity.Wallet;
import com.directrice.wallet.response.Response;
import com.directrice.wallet.service.TransactionServiceImp;
import com.directrice.wallet.service.WalletServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/directrice/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImp transactionServiceImp;


    @GetMapping("/user")
    public ResponseEntity<Response> getAllWalletById(@RequestHeader String token,
                                                     @RequestParam String walletId){
        return new ResponseEntity(new Response("All Wallet",transactionServiceImp.getUserWalletTransactions(walletId), LocalDateTime.now()),HttpStatus.OK);
    }





}
