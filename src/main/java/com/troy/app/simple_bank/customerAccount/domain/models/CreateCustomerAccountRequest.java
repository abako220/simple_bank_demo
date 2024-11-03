package com.troy.app.simple_bank.customerAccount.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

public record CreateCustomerAccountRequest(
        String accountName,
@Valid AccountRequest accountRequest,
AccountType accountType,
@Valid CustomerRequest customerRequest,
@Valid CustomerAddressRequest addressRequest
) {

}

