package hr.abysalto.hiring.mid.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User profile information.")
public record UserResponse(

        @Schema(description = "User ID in the local database.", example = "5")
        Long id,

        @Schema(description = "User email address.", example = "john.doe@example.com")
        String email,

        @Schema(description = "User first name.", example = "John")
        String firstName,

        @Schema(description = "User last name.", example = "Doe")
        String lastName
) {}
