package com.example.notification.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class EmployeesDto {
    private long id;

    private String firstName;
    private String lastName;

    private String userName;

    private String email;

    private String status;
    private String dateOfBirth;
    private String address;
    private String houseNumber;
    private String phoneNumber;
    private String password;  // Correctly named password field

}
