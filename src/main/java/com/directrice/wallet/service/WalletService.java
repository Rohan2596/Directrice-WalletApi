package com.directrice.wallet.service;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.dto.WalletSummary;
import com.directrice.wallet.entity.Wallet;

public interface WalletService {

    WalletSummary createWallet(String token);
    WalletSummary updateWallet(String token,EditWallet editWallet);
    WalletSummary getWallet(String token, String walletID);

}
