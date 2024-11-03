package com.troy.app.simple_bank.customerAccount.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troy.app.simple_bank.customerAccount.domain.InvalidCustomerException;
import com.troy.app.simple_bank.customerAccount.domain.models.CreateCustomerAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAccountValidation {

    public static void validateCustomerInformation(CreateCustomerAccountRequest customerAccount) {
        String phoneNumber = customerAccount.customerRequest().customerPhoneNumber();
        if(customerAccount.customerRequest().customerEmail() == null || customerAccount.customerRequest().customerEmail().equals("")) {
            throw new InvalidCustomerException("Customer email is required");
        }
        if(customerAccount.customerRequest().customerFirstName() == "" || customerAccount.customerRequest().customerFirstName().equals("")) {
            throw new InvalidCustomerException("Customer first name is required");
        }
        if(customerAccount.customerRequest().customerLastName() == "" || customerAccount.customerRequest().customerLastName().equals("")) {
            throw new InvalidCustomerException("Customer Last name is required");
        }
        if(customerAccount.accountRequest().openingAmount()==null || customerAccount.accountRequest().openingAmount().equals("")) {
            throw new InvalidCustomerException("Opening amount is required");
        }

        if(isValidPhoneNumber(phoneNumber) && validatePhoneNumberLength(phoneNumber)) {
            throw new InvalidCustomerException("Invalid phone number format");
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePhoneNumberLength(String phoneNumber) {
            return phoneNumber.length() == 10 || phoneNumber.length() == 14;
    }
}
