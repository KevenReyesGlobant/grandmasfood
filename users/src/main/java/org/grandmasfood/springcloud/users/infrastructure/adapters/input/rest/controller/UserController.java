package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "User", description = "This microservice handles user registration, authentication, and verification, including account creation, JWT login, and email verification.")
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

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user, saves it to the database, and sends an email verification."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered"),
            @ApiResponse(responseCode = "409", description = "Email already exists in the database"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/v1/user/register")
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

    @Operation(
            summary = "Verify user email",
            description = "Verifies the user's email using the provided token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirect to verification status page"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/v1/user/verify")
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


    @Operation(
            summary = "User login",
            description = "Authenticates the user based on the provided credentials and returns a JWT token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials or email not verified")
    })
    @PostMapping("/api/v1/user/login")
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

    @Operation(
            summary = "Logout user",
            description = "Invalidates the user's token and logs them out."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout successful"),
            @ApiResponse(responseCode = "401", description = "Invalid token")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/api/v1/user/logged")
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