package com.troy.app.simple_bank.customerAccount.domain.models;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record CustomerAccountDTO(
        String accountName,
        AccountRequest accountRequest,
        String accountStatus,
        AccountType accountType,
        CustomerRequest customerRequest,
        CustomerAddressRequest addressRequest,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
