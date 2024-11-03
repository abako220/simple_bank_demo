package com.troy.app.simple_bank.customerAccount.config;

import com.troy.app.simple_bank.customerAccount.domain.CustomerAccountNotFoundException;
import com.troy.app.simple_bank.customerAccount.domain.InvalidCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

public class CustomerAccountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerAccountNotFoundException.class)
    ProblemDetail handle (CustomerAccountNotFoundException customerAccountNotFoundException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Customer not found");
        problemDetail.setTitle("Customer Account Not Found");
        problemDetail.setStatus(404);
        problemDetail.setProperty("Timestamp", Instant.now());
        problemDetail.setDetail(customerAccountNotFoundException.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(InvalidCustomerException.class)
    ProblemDetail handle (InvalidCustomerException invalidCustomerException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid customer information");
        problemDetail.setTitle("Invalid Customer information");
        problemDetail.setStatus(400);
        problemDetail.setProperty("Timestamp", Instant.now());
        problemDetail.setDetail(invalidCustomerException.getMessage());

        return problemDetail;
    }
}
