package com.pravin.retailer.Model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private byte age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private double height;
    private double weight;
    private String eyeColor;
    @Embedded
    private Hair hair;
    private String ip;
    @Embedded
    private Address address;
    private String macAddress;
    private String university;
    @Embedded
    private Bank bank;
    @Embedded
    private Company company;
    private String ein;
    private String ssn;
    private String userAgent;
    @Embedded
    private Crypto crypto;
    private String role;


}
