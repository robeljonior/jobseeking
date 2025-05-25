package com.example.notification.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CampanyEmployeeDto {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String position;
    private boolean status;
}
