package com.troy.app.simple_bank.customerAccount.domain;

import com.troy.app.simple_bank.customerAccount.domain.models.CreateCustomerAccountRequest;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountCreationResponse;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountDTO;

public interface CustomerAccountService {
    CustomerAccountCreationResponse createAccount(CreateCustomerAccountRequest customerAccount);
    CustomerAccountDTO getCustomerByAccountNumber(String accountNumber);
}
