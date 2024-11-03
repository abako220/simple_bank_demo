package com.troy.app.simple_bank.customerAccount.domain;

import com.troy.app.simple_bank.customerAccount.domain.models.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
class CustomerAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_generator")
    @SequenceGenerator(name = "account_id_generator", sequenceName = "account_id_seq")
    private Long id;

    @Column(name = "account_number", length = 10, nullable = false)
    private String accountNumber;

    @AttributeOverrides(
            value = {

                    @AttributeOverride(name = "accountName", column = @Column(name = "account_name"))
            })
    private String accountName;

    @Embedded
    @AttributeOverrides(
            value = {

                    @AttributeOverride(name = "openingAmount", column = @Column(name = "opening_amount", columnDefinition = "Decimal(10,2) default '0.00'")),
                    @AttributeOverride(name = "balance", column = @Column(name = "balance", columnDefinition = "Decimal(10,2) default '0.00'")),

            })

    AccountRequest accountRequest;



    @Enumerated(EnumType.STRING)
    @Embedded
    @AttributeOverrides(
            value = {

                    @AttributeOverride(name = "accountType", column = @Column(name = "account_Type"))
            })
    AccountType accountType;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "customerFirstName", column = @Column(name = "customer_firstname", length = 30)),
                    @AttributeOverride(name = "customerLastName", column = @Column(name = "customer_lastname", length = 30)),
                    @AttributeOverride(name = "customerPhoneNumber", column = @Column(name = "customer_phone_number", unique = true, length = 13, nullable = false)),
                    @AttributeOverride(name = "customerEmail", column = @Column(name = "customer_email", unique = true,length = 25, nullable = false ))
            })
    private CustomerRequest customerRequest;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "streetNo", column = @Column(name = "address_street_no", length = 10)),
                    @AttributeOverride(name = "addressStreet", column = @Column(name = "address_street_name", length = 30)),
                    @AttributeOverride(name = "addressCity", column = @Column(name = "address_city", length = 13)),
                    @AttributeOverride(name = "addressState", column = @Column(name = "address_state",length = 25))
            })

    private CustomerAddressRequest addressRequest;

    @Enumerated(EnumType.STRING)
    @Embedded
            @Column(name = "account_status")
    //@AttributeOverrides(value = {@AttributeOverride(name = "account_status", column = @Column(name = "account_status"))})

    private String account_status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt =  LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
