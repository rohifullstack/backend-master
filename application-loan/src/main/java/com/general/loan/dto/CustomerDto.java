package com.general.loan.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String customerId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String mobileNumber;
    private String emailId;
    private String city;
    private String state;
    private String zipCode;
    private String adharCardNumber;
    private String monthlyIncome;
    private String loanPurpose;
    private String loanAmount;
    private String loanPeriod;
    private byte[] adharFile;
    // private MultipartFile panCard;
    // private MultipartFile paymentSlip;


}
