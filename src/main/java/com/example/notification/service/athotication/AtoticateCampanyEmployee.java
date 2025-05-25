//package com.example.notification.service.athotication;
//
//import com.example.notification.Config.JwtService;
//import com.example.notification.Repository.SubJobsRepository;
//import com.example.notification.dto.Response.EmployessResponce;
//import com.example.notification.dto.request.AuthenticationRequest;
//import com.example.notification.dto.request.EmployeesDto;
//import com.example.notification.model.CampanyEmployee;
//import com.example.notification.token.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//
//public class AtoticateCampanyEmployee {
//    @Autowired
//    private CampanyEmployiRepo agentRepository;
//    @Autowired
//    SubJobsRepository subJobsRepository;
//    @Autowired
//    private final JwtService jwtService;
//    @Autowired
//    private final TokenForCampanyEmploy tokenRepository;
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//    public EmployessResponce createEmployee(EmployeesDto request) {
//        var agents = CampanyEmployee.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .userName(request.getUserName())
//                .email(request.getEmail())
//                .status(true)
//                .phoneNumber(request.getPhoneNumber())
//                .password(request.getPassword())
//                .role(request.getRole())
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
//    }
//
//    private void saveUserToken(CampanyEmployee saveuser, String jwtToken) {
//        var token = TokenCampanyEmployi.builder()
//                .user(saveuser)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }
//    public EmployessResponce authenticate(AuthenticationRequest request) {
//        // Initialize response object
//        EmployessResponce responce = new EmployessResponce();
//
//        try {
//            // Authenticate the user
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()
//                    )
//            );
//
//            // Check if the agent exists in the repository
//            var agent = agentRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
//                responce.setStatusDesc("Incorrect email: " + request.getEmail());
//                throw new RuntimeException("Check the email address");
//            });
//
//            // Generate JWT tokens
//            var jwtToken = jwtService.generateToken(agent);
//            var refreshToken = jwtService.generateRefreshToken(agent);
//
//            // Revoke old tokens and save new ones
//            revokeAllUserTokens(agent);
//            saveUserToken(agent, jwtToken);
//
//            // Set response status and tokens
//            responce.setStatusDesc("Welcome to the system, " + agent.getUsername());
//            responce.setAccessToken(jwtToken);
//            responce.setRefreshToken(refreshToken);
//
//        } catch (Exception e) {
//            // Handle any errors
//            responce.setStatusDesc("Authentication failed: " + e.getMessage());
//            // You can also log the exception for debugging purposes.
//        }
//
//        // Return the response object
//        return responce;
//    }
//
//    private void revokeAllUserTokens(CampanyEmployee agent) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(agent.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
//}
