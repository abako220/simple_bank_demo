package com.troy.app.simple_bank.customer.web.controllers;

import com.troy.app.simple_bank.customerAccount.domain.CustomerAccountService;
import com.troy.app.simple_bank.customerAccount.domain.models.*;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.boot.test.context.SpringBootTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ApplicationModuleTest(webEnvironment = WebEnvironment.RANDOM_PORT, mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@AutoConfigureMockMvc
@AllArgsConstructor
public class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomerAccountService customerAccountService;

    @BeforeEach
    void setUp() {
        // Mock any necessary dependencies
        CustomerRequest customerRequest = new CustomerRequest("John", "Doe", "john@gmail.com", "07011111111");
        AccountRequest accountRequest = new AccountRequest(new BigDecimal("30.00"), new BigDecimal("30.00"));
        CustomerAddressRequest addressRequest =  new CustomerAddressRequest("44", "Bay Garden", "Java City", "USA");
        String accountName = customerRequest.customerFirstName()
                .concat(" ").concat(customerRequest.customerLastName());
        CreateCustomerAccountRequest createCustomerAccountRequest = new CreateCustomerAccountRequest(accountName,accountRequest, AccountType.SAVINGS, customerRequest, addressRequest);
        //BDDMockito.given(customerAccountService.createCustomerAccount(createCustomerAccountRequest)).willReturn(getCreatedCustomer());

    }


    @Test
    void shouldGetCustomerAccountSuccessfullyCreated() throws Exception {
        mvc.perform(post("/api/v1/customers/account/opening")
               .contentType("application/json")
               .content("""
                      
                       {
                       "customerAccountRequest":
                                  {"balance":30.00,
                                  "openingAmount":30.00
                                  },
                       "customerAccountType":"SAVINGS",
                       "customerRequest":{
                                  "firstName":"John",
                                  "lastName":"Doe",
                                  "email":"john@gmail.com",
                                  "phoneNumber":"07011111111"
                                  },
                       "customerAddressRequest":{
                                  "streetNo":"44",
                                  "addressStreet":"Bay Garden",
                                  "addressCity":"Java City",
                                  "addressState":"USA"
                                  },
                       "customerAccountStatus":"ACTIVE"
                       }
                       
                       """)
               .accept("application/json"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.customerAccountRequest.account.balance").value(30.00))
               .andExpect(jsonPath("$.customerAccountRequest.status").value("ACTIVE"));

    }

    @Test
    void shouldReturn400ForInvalidCustomerRequest() throws Exception {
        mvc.perform(post("/api/v1/customers/account/opening")
               .contentType("application/json")
               .content("""
                       {
                      
                       "customerAccountRequest":
                                  {"balance":-30.00,
                                  "openingAmount":-30.00
                                  },
                       "customerAccountType":"SAVINGS",
                       "customerRequest":{
                                  "firstName":"9John",
                                  "lastName":"Doe",
                                  "email":"invalid_email@gmail.com",
                                  "phoneNumber":"-07011111111"
                                  },
                       "customerAddressRequest":{
                                  "streetNo":"44",
                                  "addressStreet":"Bay Garden",
                                  "addressCity":"Java City",
                                  "addressState":"USA"
                                  },
                       "customerAccountStatus":"ACTIVE"
           
                       }
                       """)
               .accept("application/json"))
               .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenCustomerAccountNumberDoesNotExist() throws Exception {
        mvc.perform(get("/api/v1/customers/account/000000000000000000000000")
               .accept("application/json"))
               .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetCustomerAccountDetailsSuccessfullyCreated() throws Exception {
        CreateCustomerAccountRequest request = getCreatedCustomer();
        CustomerAccountCreationResponse response = customerAccountService.createAccount(request);

        mvc.perform(get("/api/v1/customers/account/{accountNumber}", response.accountNumber())
               .accept("application/json"))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(response.accountNumber()))
                .andExpect(jsonPath("$.phoneNumber").value(response.phoneNumber()));

    }

    private static CreateCustomerAccountRequest getCreatedCustomer() {
        CustomerRequest customerRequest = new CustomerRequest("Jayden", "Abako", "jayden@gmail.com", "07123455556");
        AccountRequest accountRequest = new AccountRequest(new BigDecimal("50.00"), new BigDecimal("50.00"));
        CustomerAddressRequest addressRequest =  new CustomerAddressRequest("36", "Union Berlin", "Berlin", "Germany");
        String accountName = customerRequest.customerFirstName()
                .concat(" ").concat(customerRequest.customerLastName());
        return new CreateCustomerAccountRequest(accountName,accountRequest, AccountType.SAVINGS, customerRequest, addressRequest);
    }



}
