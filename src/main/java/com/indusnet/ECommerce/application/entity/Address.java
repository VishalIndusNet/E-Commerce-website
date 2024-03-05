package com.indusnet.ECommerce.application.entity;

import  com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name="first_name")
    private String firstName;

    @Column
    private String lastName;

   private String city;

   private String state;
   private String zipcode;

   private String streetAddress;
   @Column(name = "mobile_number")
   private Long mobile;

   @ManyToOne
   @JsonIgnore
   @JoinColumn(name = "user_id")
    private User user;

}
