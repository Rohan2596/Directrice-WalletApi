package com.directrice.wallet.exception;

import com.directrice.wallet.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalWalletException {

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<Response> handleWalletException(WalletException walletException){
        return new ResponseEntity<>(
                new Response(walletException.exceptionTypes.errorMessage,walletException.exceptionTypes, LocalDateTime.now()),
                HttpStatus.BAD_GATEWAY);
    }


}
