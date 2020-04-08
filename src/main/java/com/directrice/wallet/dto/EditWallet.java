package com.directrice.wallet.dto;

import com.directrice.wallet.enumeration.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditWallet {

    @NotEmpty(message = "Wallet Id cannot be empty.")
    @NotNull(message = "Wallet Id cannot be empty.")
    private String walletId;
    private float amount;
    @NotEmpty(message = "Transaction Status cannot be empty.")
    @NotNull(message = "Transaction Status cannot be empty.")
    private String transactionStatus;
}
