package hr.abysalto.hiring.mid.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Current authenticated user's cart.")
public record CartResponse(
        @Schema(description = "Cart ID in local database.", example = "10")
        Long cartId,

        @Schema(description = "All items currently in the cart.")
        List<CartItemResponse> items
) {}
