package com.directrice.wallet.controller;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.dto.WalletSummary;
import com.directrice.wallet.entity.Wallet;
import com.directrice.wallet.response.Response;
import com.directrice.wallet.service.WalletServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directrice/wallet")
public class WalletController {

    @Autowired
    private WalletServiceImp walletServiceImp;

    @PostMapping
    public ResponseEntity<Response> createWallet(@RequestHeader String token){

        WalletSummary walletSummary=walletServiceImp.createWallet(token);
        return new ResponseEntity(new Response("Created",walletSummary), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Response> updateWallet(@RequestHeader String token,
                                                 @RequestBody EditWallet editWallet){
       WalletSummary walletSummary= walletServiceImp.updateWallet(token,editWallet);
        return new ResponseEntity(new Response("Updated",walletSummary), HttpStatus.OK);
    }

    @GetMapping("/walletId")
    public ResponseEntity<Response> getAllWalletById(@RequestHeader String token,
                                                     @RequestParam String walletId){
        return new ResponseEntity(new Response("All Wallet",walletServiceImp.getWallet(token,walletId)),HttpStatus.OK);
    }


}
