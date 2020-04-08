package com.directrice.wallet.exception;

public class WalletException extends RuntimeException {

    public ExceptionTypes exceptionTypes;
    public WalletException(ExceptionTypes exceptionTypes){
        this.exceptionTypes=exceptionTypes;
    }
    public enum ExceptionTypes{
        INVALID_WALLET_ID("Invalid Wallet Id."),
        INSUFFICIENT_BALANCE("InSufficient Amount To Send");
        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage=errorMessage;
        }
    }

}
