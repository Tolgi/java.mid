package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.dto.auth.AuthResponse;
import hr.abysalto.hiring.mid.dto.auth.LoginRequest;
import hr.abysalto.hiring.mid.dto.auth.RegisterRequest;
import hr.abysalto.hiring.mid.dto.auth.UserResponse;
import hr.abysalto.hiring.mid.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse me() {
        return authService.me();
    }
}
