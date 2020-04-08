package com.directrice.wallet.entity;

import com.directrice.wallet.enumeration.TransactionType;
import com.directrice.wallet.enumeration.WalletStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Wallet {


    @Id
    private  String walletId;
    private float amount;
    private String userId;
    private String walletStatus;
    private String transactionMessage;
    private LocalDateTime createdTimeStamp=LocalDateTime.now();
    private LocalDateTime updatedTimeStamp;

    public Wallet(String userId) {
        this.amount=0000;
        this.userId=userId;
        this.walletStatus= WalletStatus.CREATED.name();
        this.updatedTimeStamp=LocalDateTime.now();
        this.transactionMessage= WalletStatus.CREATED.name();
    }
}
