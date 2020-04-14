package com.directrice.wallet.response;

import lombok.*;

import java.time.LocalDateTime;


@Data
public class Response {

    private String message;
    private Object data;

    public Response(String message, Object data, LocalDateTime timeStamp) {
        this.message = message;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    private LocalDateTime timeStamp=LocalDateTime.now();


    public Response() {
    }
}