package com.example.notification.service;

import com.example.notification.Repository.AgentRepository;
import com.example.notification.Repository.SubJobsRepository;
import com.example.notification.dto.Response.EmployessResponce;
import com.example.notification.dto.request.EmployeesDto;
import com.example.notification.model.Agent.Agents;
import com.example.notification.response.BaseResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeesService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubJobsRepository subJobsRepository;


    // Convert entity to DTO
    private EmployeesDto convertToDto(Agents employee) {
        return modelMapper.map(employee, EmployeesDto.class);
    }

    // Get all employees
    public List<EmployeesDto> getAllEmployees() {
        List<Agents> employees = agentRepository.findAll();
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public Optional<EmployeesDto> getEmployeeById(Long id) {
        Optional<Agents> employee = agentRepository.findById(id);
        return employee.map(this::convertToDto);
    }

    // Get employee by name (email)
    public Optional<EmployeesDto> getEmployeeByName(String username) {
        Optional<Agents> employee = agentRepository.findByEmail(username);
        return employee.map(this::convertToDto);
    }

    // Create new employee
    @Transactional
    public BaseResponseDto createEmployee(EmployeesDto request) {
        BaseResponseDto response = validateRequest(request);

        if (!response.isStatus()) {
            return response;
        }


        Optional<Agents> existing = agentRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            response.setStatus(false);
            response.setStatusDesc("Agent with email '" + request.getEmail() + "' already exists.");
            return response;
        }

        var newAgent = Agents.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .status(request.getStatus())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();

        var savedAgent = agentRepository.save(newAgent);

        response.setStatus(true);
        response.setStatusDesc("Account successfully created.");
        response.setData(convertToDto(savedAgent));
        return response;
    }

    private response chakeemail(String email) {
    }


    // Update employee
    public BaseResponseDto updateEmployee(EmployeesDto employeesDto) {
        BaseResponseDto response = validateRequest(employeesDto);

        if (!response.isStatus()) {
            return response;
        }

        Agents employee = agentRepository.findById(employeesDto.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<Agents> existingByEmail = agentRepository.findByEmail(employeesDto.getEmail());
        if (existingByEmail.isPresent() ) {
            response.setStatus(false);
            response.setStatusDesc("Email '" + employeesDto.getEmail() + "' is already in use by another employee.");
            return response;
        }

        employee.setFirstName(employeesDto.getFirstName());
        employee.setLastName(employeesDto.getLastName());
        employee.setStatus(employeesDto.getStatus());
        employee.setDateOfBirth(employeesDto.getDateOfBirth());
        employee.setAddress(employeesDto.getAddress());
        employee.setHouseNumber(employeesDto.getHouseNumber());
        employee.setPhoneNumber(employeesDto.getPhoneNumber());
        employee.setPassword(employeesDto.getPassword());

        Agents updatedEmployee = agentRepository.save(employee);

        response.setStatus(true);
        response.setStatusDesc("Employee updated successfully.");
        response.setData(convertToDto(updatedEmployee));
        return response;
    }

    // Delete employee
    @Transactional
    public void deleteEmployee(Long id) {
        agentRepository.deleteById(id);
    }

    // Validate request fields
    private BaseResponseDto validateRequest(EmployeesDto request) {
        BaseResponseDto response = new BaseResponseDto();

        if (request.getFirstName() == null || request.getFirstName().isBlank() ||
                request.getLastName() == null || request.getLastName().isBlank() ||
                request.getEmail() == null || request.getEmail().isBlank() ||
                request.getPhoneNumber() == null || request.getPhoneNumber().isBlank() ||
                request.getPassword() == null || request.getPassword().isBlank()) {

            response.setStatus(false);
            response.setStatusDesc("Missing required fields. Please ensure all fields are filled.");
            return response;
        }

        response.setStatus(true);
        return response;
    }
}

