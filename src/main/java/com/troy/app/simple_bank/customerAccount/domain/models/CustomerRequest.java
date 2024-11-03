package com.troy.app.simple_bank.customerAccount.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerRequest (
        @NotBlank(message = "Customer first name is required") String customerFirstName,
        @NotBlank(message = "Customer last name is required") String customerLastName,
        @NotBlank(message = "Customer phone number is required") String customerPhoneNumber,
        @NotBlank(message = "Customer email is required") String customerEmail
){
}
