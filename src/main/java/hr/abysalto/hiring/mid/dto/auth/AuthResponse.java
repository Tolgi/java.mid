package hr.abysalto.hiring.mid.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response containing an access token.")
public record AuthResponse(
        @Schema(description = "JWT token. Use: Authorization: Bearer <token>", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken
) {}
