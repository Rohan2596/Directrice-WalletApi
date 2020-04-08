package com.directrice.wallet.controller;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.dto.WalletSummary;
import com.directrice.wallet.response.Response;
import com.directrice.wallet.service.WalletServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/directrice/wallet")
public class WalletController {

    @Autowired
    private WalletServiceImp walletServiceImp;

    @PostMapping
    public ResponseEntity<Response> createWallet(@RequestHeader String token){

        WalletSummary walletSummary=walletServiceImp.createWallet(token);
        return new ResponseEntity(new Response("Wallet Created Successfully.",walletSummary), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> updateWallet(@RequestHeader String token,
                                                 @RequestBody @Valid EditWallet editWallet,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
       WalletSummary walletSummary= walletServiceImp.updateWallet(token,editWallet);
        return new ResponseEntity(new Response("Wallet Updated Successfully.",walletSummary), HttpStatus.OK);
    }

    @GetMapping("/walletId")
    public ResponseEntity<Response> getAllWalletById(@RequestHeader String token,
                                                     @RequestParam String walletId){
        return new ResponseEntity(new Response("User Wallet Details.",walletServiceImp.getWallet(token,walletId)),HttpStatus.OK);
    }


}
