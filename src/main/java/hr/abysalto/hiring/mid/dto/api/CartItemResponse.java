package hr.abysalto.hiring.mid.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A single cart line item with quantity and product details.")
public record CartItemResponse(
        @Schema(description = "Product ID from DummyJSON.", example = "1")
        Long productId,

        @Schema(description = "Quantity of this product in the cart.", example = "2")
        int quantity,

        @Schema(description = "Full product details (fetched from DummyJSON, cached).")
        ProductResponse product
) {}
