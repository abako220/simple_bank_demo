package com.troy.app.simple_bank.customerAccount.domain.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record CustomerAddressRequest(
        String streetNo,
        @NotBlank(message = "Customer address street name is required") String addressStreet,
        @NotBlank(message = "Customer address city is required") String addressCity,
        @NotBlank(message = "Customer address state is required") String addressState
) {
}
