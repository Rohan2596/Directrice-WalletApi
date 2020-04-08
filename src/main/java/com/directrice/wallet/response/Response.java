package com.directrice.wallet.response;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {

    private String message;
    private Object data;
}