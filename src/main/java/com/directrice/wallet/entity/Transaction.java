package com.directrice.wallet.entity;

import com.directrice.wallet.dto.EditWallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document
public class Transaction {

    @Id
    private String transactionID;
    private String transactionMessage;
    private float amount;
    private String walletId;
    private String userId;
    private LocalDateTime createdTimeStamp=LocalDateTime.now();



}
