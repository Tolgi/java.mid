package hr.abysalto.hiring.mid.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "User registration request.")
public record RegisterRequest(

        @Schema(description = "User email.", example = "john.doe@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @Schema(description = "User password (min 8 chars).", example = "Password123!")
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password,

        @Schema(description = "First name.", example = "John")
        @NotBlank(message = "First name is required")
        String firstName,

        @Schema(description = "Last name.", example = "Doe")
        @NotBlank(message = "Last name is required")
        String lastName

) {}
