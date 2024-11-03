package com.troy.app.simple_bank.customerAccount.domain;

import com.troy.app.simple_bank.customerAccount.domain.models.AccountStatus;
import com.troy.app.simple_bank.customerAccount.domain.models.CreateCustomerAccountRequest;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountDTO;
import lombok.Builder;

@Builder
class CustomerAccountMapper {

    static CustomerAccountEntity convertToEntity(CreateCustomerAccountRequest customerAccount) {
        return CustomerAccountEntity.builder()
                .accountNumber(System.currentTimeMillis()+ "")
                .accountName(customerAccount.customerRequest().customerFirstName()
                        .concat(" ").concat(customerAccount.customerRequest().customerLastName()))
                .accountRequest(customerAccount.accountRequest())
                .account_status(AccountStatus.INCOMPLETE.name())
                .accountType(customerAccount.accountType())
                .customerRequest(customerAccount.customerRequest())
                .addressRequest(customerAccount.addressRequest())
                .build();
    }

    static CustomerAccountDTO convertToDto(CustomerAccountEntity customerAccount) {
        return CustomerAccountDTO.builder()
                .accountRequest(customerAccount.getAccountRequest())
                .accountStatus(customerAccount.getAccount_status())
                .accountType(customerAccount.getAccountType())
                .customerRequest(customerAccount.getCustomerRequest())
                .addressRequest(customerAccount.getAddressRequest())
                .accountName(customerAccount.getAccountName())
                .build();
    }
}
