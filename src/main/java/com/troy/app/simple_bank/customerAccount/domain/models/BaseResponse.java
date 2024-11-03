package com.troy.app.simple_bank.customerAccount.domain.models;

import lombok.Builder;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Builder
@ToString
public class BaseResponse<T> {
    public String message;
    public T data;
    public HttpStatus status;

    public BaseResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BaseResponse(String message, T data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
}
