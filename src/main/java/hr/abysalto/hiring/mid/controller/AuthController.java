package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.dto.auth.AuthResponse;
import hr.abysalto.hiring.mid.dto.auth.LoginRequest;
import hr.abysalto.hiring.mid.dto.auth.RegisterRequest;
import hr.abysalto.hiring.mid.dto.auth.UserResponse;
import hr.abysalto.hiring.mid.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Auth", description = "Registration, login and current-user info.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", description = "Creates a new user account in the database.")
    @ApiResponse(responseCode = "200", description = "User created")
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Login", description = "Returns a JWT access token. Use it in Swagger Authorize: Bearer <token>.")
    @ApiResponse(responseCode = "200", description = "Logged in")
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Get current user", description = "Returns profile information for the currently authenticated user.")
    @ApiResponse(responseCode = "200", description = "Current user")
    @GetMapping("/me")
    public UserResponse me() {
        return authService.me();
    }
}
