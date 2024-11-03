package com.troy.app.simple_bank.customerAccount.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troy.app.simple_bank.customerAccount.domain.CustomerAccountService;
import com.troy.app.simple_bank.customerAccount.domain.models.BaseResponse;
import com.troy.app.simple_bank.customerAccount.domain.models.CreateCustomerAccountRequest;
import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountCreationResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/customer")
@RestController
//@RequiredArgsConstructor
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;
    private final Logger logger = LoggerFactory.getLogger(CustomerAccountController.class);
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @PostMapping("/account/opening")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CustomerAccountCreationResponse> createCustomerAccount(@Valid @RequestBody CreateCustomerAccountRequest createCustomerAccountRequest) {
        logger.info("Creating new account with request: {}", createCustomerAccountRequest.toString());
        CustomerAccountCreationResponse response = customerAccountService.createAccount(createCustomerAccountRequest);
        logger.info("Account created: {}", response);
        return BaseResponse.<CustomerAccountCreationResponse>builder()
                .message("Account created successfully")
                .data(response)
                .status(HttpStatus.CREATED)
                .build();
        //return new BaseResponse<>("Account created successfully", response, HttpStatus.CREATED);

    }






}
