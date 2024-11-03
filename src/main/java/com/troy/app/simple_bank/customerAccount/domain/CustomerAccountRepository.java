package com.troy.app.simple_bank.customerAccount.domain;

import com.troy.app.simple_bank.customerAccount.domain.models.CustomerAccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface CustomerAccountRepository extends JpaRepository<CustomerAccountEntity, Long> {
    @Query(
            value = """
                    SELECT DISTINCT c
                    FROM CustomerAccountEntity c
                    where c.accountNumber = :accountNumber
                    """)
    Optional<CustomerAccountEntity> findCustomerAccountEntitiesByAccountNumber(String accountNumber);
}
