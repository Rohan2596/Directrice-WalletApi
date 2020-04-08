package com.directrice.wallet.dto;

import com.directrice.wallet.enumeration.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditWallet {

    private String walletId;
    private float amount;
    private String transactionStatus;
}
