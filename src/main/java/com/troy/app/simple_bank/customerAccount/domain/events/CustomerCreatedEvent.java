package com.troy.app.simple_bank.customerAccount.domain.events;

public record CustomerCreatedEvent(
        String accountNumber,
        String customerName,
        String phoneNumber) {
}
