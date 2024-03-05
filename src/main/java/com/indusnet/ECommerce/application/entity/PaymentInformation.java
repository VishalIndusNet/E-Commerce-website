package com.indusnet.ECommerce.application.entity;


import jakarta.persistence.Column;
import lombok.Data;


import java.time.LocalDate;

@Data public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardHolderName;
    private  String cardNumber;
    private String cvv;
    private LocalDate expirationDate;
}
