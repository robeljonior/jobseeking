//package com.example.notification.service.athotication;
//
//import com.example.notification.Config.JwtService;
//import com.example.notification.dto.CampanyDto;
//import com.example.notification.dto.Response.CampanyResponse;
//import com.example.notification.dto.Response.EmployessResponce;
//import com.example.notification.dto.request.AuthenticationRequest;
//import com.example.notification.dto.request.EmployeesDto;
//import com.example.notification.model.Agent.Agents;
//import com.example.notification.enums.Role;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AthoticateforCampany {
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//    @Autowired
//    private final JwtService jwtService;
//
//
//    public CampanyResponse createEmployee(CampanyDto request) {
//        var agents = Agents.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .userName(request.getUserName())
//                .email(request.getEmail())
//                .address(request.getAddress())
//                .houseNumber(request.getHouseNumber())
//                .phoneNumber(request.getPhoneNumber())
//                .password(request.getPassword())
//                .role(Role.valueOf("AGENT"))
//                .build();
//        var saveuser = agentRepository.save(agents);
//        var jwtToken = jwtService.generateToken(agents);
//        var refreshToken = jwtService.generateRefreshToken(agents);
//        saveUserToken(saveuser, jwtToken);
//        var responce = EmployessResponce.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .userName(request.getUserName())
//                .statusDesc("this user saved soccos fully")
//                .build();
//
//        return responce;
//
//    }
//
//    public CampanyResponse authenticate(AuthenticationRequest request) {
//    }
//}
