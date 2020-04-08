package com.directrice.wallet.dto;

import com.directrice.wallet.entity.Wallet;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class WalletSummary  {

    private String walletId;
    private float amount;
    private String userId;
    private String walletStatus;
    private String transactionStatus;
    private LocalDateTime timeStamp;


    public WalletSummary(Wallet wallet) {
        this.walletId=wallet.getWalletId();
        this.amount=wallet.getAmount();
        this.userId=wallet.getUserId();
        this.timeStamp=wallet.getUpdatedTimeStamp();
        this.walletStatus=wallet.getWalletStatus();
        this.transactionStatus=wallet.getTransactionMessage();
    }
}
