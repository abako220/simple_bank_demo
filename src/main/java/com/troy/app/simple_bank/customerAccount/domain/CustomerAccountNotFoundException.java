package com.troy.app.simple_bank.customerAccount.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerAccountNotFoundException extends RuntimeException {
    public CustomerAccountNotFoundException(String message) {
        super(message);
    }

    public static CustomerAccountNotFoundException forCustomerAccount(String accountNumber) {
        return new CustomerAccountNotFoundException(String.format("Customer with account number '%s' not found", accountNumber));
    }

}
