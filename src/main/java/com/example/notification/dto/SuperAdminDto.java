package com.example.notification.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SuperAdminDto {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String status;
}
