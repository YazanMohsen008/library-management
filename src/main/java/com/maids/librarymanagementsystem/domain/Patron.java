package com.maids.librarymanagementsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

@Table(name = "Patron")
public class Patron extends GenericDomain <Integer>{

    @Column(name = "name")

    @NotNull
    @Size(min = 5, max = 30)
    private String name;

    @Column(name = "email_address")
    @Email
    private String emailAddress;

    @Column(name = "phone_number")
    @NotNull
    @Pattern(regexp = "09\\d{8}")
    @Size(min = 10, max = 10)
    private String phoneNumber;

    public Patron() {
    }

    public Patron(@NotNull @Size(min = 5, max = 30) String name, @Email String emailAddress, @NotNull @Pattern(regexp = "09*") @Size(min = 10, max = 10) String phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
