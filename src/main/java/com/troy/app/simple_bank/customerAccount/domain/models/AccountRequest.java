package com.troy.app.simple_bank.customerAccount.domain.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Setter;

import java.math.BigDecimal;

public record AccountRequest(
  @Column(nullable = false)
  BigDecimal openingAmount,

  BigDecimal balance

) {
}


