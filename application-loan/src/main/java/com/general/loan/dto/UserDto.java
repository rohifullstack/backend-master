package com.general.loan.dto;

import com.general.loan.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {

    private String FirstName;
    private String LastName;
    private String email;
    private String mobileNumber;
    private String userId;
    private String userPassword;
    private String status;
    private String creationDate;
    private String updatedDate;
    private UserEntity.UserType userType;

}

