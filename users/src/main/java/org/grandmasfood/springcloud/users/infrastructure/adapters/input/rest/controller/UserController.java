package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.users.application.ports.input.UserServicesPort;
import org.grandmasfood.springcloud.users.application.services.TokenServices;
import org.grandmasfood.springcloud.users.domain.model.DataJwtValidation;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.config.EmailSender;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request.UserCreateRequestDTO;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request.UserLoginRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserServicesPort userServicesPort;
    private final UserRestMapper userRestMapper;
    private final TokenServices tokenServices;
    private final AuthenticationManager authenticationManager;
    private final EmailSender emailSender;

    public UserController(UserServicesPort userServicesPort, UserRestMapper userRestMapper, TokenServices tokenServices, AuthenticationManager authenticationManager,
                          EmailSender emailSender) {
        this.userServicesPort = userServicesPort;
        this.userRestMapper = userRestMapper;
        this.tokenServices = tokenServices;
        this.authenticationManager = authenticationManager;
        this.emailSender = emailSender;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        try {
            User user = userRestMapper.toUser(userCreateRequestDTO);
            User savedUser = userServicesPort.save(user);

            emailSender.sendValidateEmail(savedUser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userRestMapper.toUserResponseDTO(savedUser));

        } catch (Exception e) {

            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry") &&
                    e.getMessage().contains("email")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User found in the database, please verify the registration or verify at email duplicated.");
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Register error: " + e.getMessage());
        }
    }

    @GetMapping("/user/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam(required = true) String token) {
        try {
            boolean verified = emailSender.verifyEmail(token);
            if (verified) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "/")
                        .build();
            }
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/verification-failed")
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification error");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserLoginRequestDTO userdata) {
        try {
            Authentication token = new UsernamePasswordAuthenticationToken(userdata.getEmail(), userdata.getPassword());

            Authentication loginAuth = authenticationManager.authenticate(token);

            User user = (User) loginAuth.getPrincipal();

            String jwtToken = tokenServices.generatedToken(user);

            return ResponseEntity.ok(new DataJwtValidation(jwtToken));

        } catch (AuthenticationException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials or email not verified.");
        }
    }

    @PostMapping("/user/logged")
    public ResponseEntity<?> loggedUser(@RequestHeader("Authorization") String token) {
        try {
            tokenServices.invalidatedToken(token);
            return ResponseEntity.ok().body("Logged out successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token.");
        }

    }

}