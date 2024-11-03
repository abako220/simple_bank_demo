package com.troy.app.simple_bank.customerAccount.domain;

import com.troy.app.simple_bank.customerAccount.domain.events.CustomerCreatedEvent;
import com.troy.app.simple_bank.customerAccount.domain.models.AccountStatus;
import com.troy.app.simple_bank.customerAccount.domain.models.CreateCustomerAccountRequest;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountCreationResponse;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountDTO;
import com.troy.app.simple_bank.customerAccount.validation.CustomerAccountValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerAccountServiceImpl extends CustomerAccountValidation implements CustomerAccountService {

    private ApplicationEventPublisher eventPublisher;
    private final CustomerAccountRepository customerAccountRepository;


    @Override
    public CustomerAccountCreationResponse createAccount(CreateCustomerAccountRequest customerAccount) {
        // validate the customer account information
        validateCustomerInformation(customerAccount);
        String accountName = customerAccount.customerRequest().customerFirstName().concat(""+ customerAccount.customerRequest().customerLastName());
        // create a new customer account entity and save it to the database
        CustomerAccountEntity entity = CustomerAccountMapper.convertToEntity(customerAccount);
        //entity.setAccount_status(AccountStatus.INCOMPLETE.name());
        System.out.println("  Entity result: " + entity.toString());
        //entity.getAccountRequest().accountName().concat(accountName);
        CustomerAccountEntity savedCustomer = customerAccountRepository.save(entity);
        // publish a CustomerCreatedEvent event
        log.info("Created account with accountNumber={}", savedCustomer.getAccountNumber());
        String accountNumber = savedCustomer.getAccountNumber();
        String customerName = savedCustomer.getCustomerRequest().customerFirstName()
                .concat(" " + customerAccount.customerRequest().customerLastName());
        String phoneNumber = savedCustomer.getCustomerRequest().customerPhoneNumber();

        log.info("Publishing new customer information={},{},{}", accountNumber, customerName, phoneNumber);
        eventPublisher.publishEvent(
                new CustomerCreatedEvent(accountNumber, customerName, phoneNumber));

        // return the customer account creation response with the account number, customer name, and phone number
        return new CustomerAccountCreationResponse(accountNumber, customerName, phoneNumber);
    }

    @Override
    public CustomerAccountDTO getCustomerByAccountNumber(String accountNumber) {
        return null;
    }


}
