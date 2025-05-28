package com.example.notification.model.Agent;


import com.example.notification.dto.request.EmployeesDto;
import com.example.notification.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agents")
public class Agents  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;



    @Column(name = "email", nullable = false)
    private String email;

    private String status;

    private String dateOfBirth;
    private String address;
    private String houseNumber;
    private String phoneNumber;

    @Column(name = "password")
    private String password;



    @Enumerated(EnumType.STRING)
    private Role role;



    // Method to convert Employees entity to EmployeesDto
    public EmployeesDto toDto() {
        EmployeesDto response = new EmployeesDto();

        response.setId(id);
        response.setFirstName(firstName);
        response.setLastName(lastName);
        response.setEmail(email);
        response.setStatus(status);
        response.setDateOfBirth(dateOfBirth);
        response.setAddress(address);
        response.setHouseNumber(houseNumber);
        response.setPhoneNumber(phoneNumber);
        response.setPassword(password); // Ensure to handle the password secure

        return response;
    }
}
