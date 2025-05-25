package com.example.notification.controller;

import com.example.notification.dto.request.EmployeesDto;
import com.example.notification.dto.Response.EmployessResponce;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    EmployeesService service;

    @PostMapping("/create_user")
    public BaseResponseDto create(@RequestBody EmployeesDto employeesDto){
        BaseResponseDto responseDto = service.createEmployee(employeesDto);

        return responseDto;
    }

    // 🔹 Get all employees
    @GetMapping("/get_all")
    public List<EmployeesDto> getAll() {
        return service.getAllEmployees();
    }

    // 🔹 Get employee by ID
    @PostMapping("/get_by_id")
    public EmployeesDto getById(@RequestBody Long id) {
        return service.getEmployeeById(id).orElse(null);
    }

    // 🔹 Get employee by username
    @GetMapping("/get_by_username/{username}")
    public EmployeesDto getByUsername(@RequestBody String username) {
        return service.getEmployeeByName(username).orElse(null);
    }

    // 🔹 Update employee
    @PutMapping("/update")
    public BaseResponseDto update(@RequestBody EmployeesDto employeesDto) {

        BaseResponseDto responseDto = service.updateEmployee( employeesDto);
        return responseDto;
    }

    // 🔹 Delete employee
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Employee deleted successfully";
    }

}
