package hr.abysalto.hiring.mid.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Single product details returned from DummyJSON.")
public record ProductResponse(
        @Schema(description = "Product ID from DummyJSON.", example = "1")
        Long id,

        @Schema(description = "Product title/name.", example = "iPhone 9")
        String title,

        @Schema(description = "Product brand.", example = "Apple")
        String brand,

        @Schema(description = "Product description.", example = "An apple mobile which is nothing like apple.")
        String description,

        @Schema(description = "Product price.", example = "549.99")
        double price,

        @Schema(description = "Thumbnail image URL.", example = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg")
        String thumbnail
) {}
