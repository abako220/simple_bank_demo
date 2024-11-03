package com.troy.app.simple_bank.customerAccount.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCustomerException  extends RuntimeException{
    public InvalidCustomerException(String message) {
        super(message);
    }
    public static InvalidCustomerException forInvalidCustomer(String name) {
        return new InvalidCustomerException(String.format("Invalid customer name '%s'", name));
    }
}
