package hr.abysalto.hiring.mid.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request.")
public record LoginRequest(

        @Schema(description = "User email.", example = "john.doe@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @Schema(description = "User password.", example = "Password123!")
        @NotBlank(message = "Password is required")
        String password
) {}
