package com.general.loan.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document("user")
public class UserEntity {

    private String FirstName;
    private String LastName;
    private String email;
    private String mobileNumber;
    private String userId;
    private String userPassword;
    private Status status;
    private String creationDate;
    private String updatedDate;
    private UserType userType;

    public enum Status {
        ACTIVE,
        INACTIVE,
    }

    public enum UserType {
        ADMIN,
        AGENT,
    }

}
