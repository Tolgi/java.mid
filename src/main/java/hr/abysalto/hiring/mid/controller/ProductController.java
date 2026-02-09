package hr.abysalto.hiring.mid.controller;


import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.dto.api.ProductListResponse;
import hr.abysalto.hiring.mid.exception.BadRequestException;
import hr.abysalto.hiring.mid.service.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import hr.abysalto.hiring.mid.configuration.SwaggerConfig;

@Tag(name = "Products", description = "Product retrieval from DummyJSON (cached).")
@SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of("id", "title", "price", "rating");

    private final ProductService productService;

    @Operation(
            summary = "Get products",
            description = """
                    Retrieves products from DummyJSON (with caching).
                    Supports pagination and optional sorting.
                    """
    )
    @GetMapping
    public ProductListResponse getProducts(
            @Parameter(description = "How many products to skip (pagination offset).", example = "0")
            @RequestParam(defaultValue = "0") @Min(0) int skip,

            @Parameter(description = "How many products to return.", example = "20")
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit,

            @Parameter(description = "Sort field (allowed: id, title, price, rating).", example = "price")
            @RequestParam(required = false) String sortBy,

            @Parameter(description = "Sort order.", example = "asc")
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        String normalizedSortBy = normalizeSortBy(sortBy);
        String normalizedOrder = normalizeOrder(order);

        return productService.getProducts(skip, limit, normalizedSortBy, normalizedOrder);
    }

    @Operation(summary = "Get product by id", description = "Retrieves a single product from DummyJSON (cached).")
    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @Parameter(description = "Product ID from DummyJSON.", example = "1")
            @PathVariable Long id) {
        return productService.getProductById(id);
    }

    private String normalizeSortBy(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) return null;

        String normalized = sortBy.trim().toLowerCase(Locale.ROOT);
        if (!ALLOWED_SORT_FIELDS.contains(normalized)) {
            throw new BadRequestException("Invalid sortBy. Allowed: " + ALLOWED_SORT_FIELDS);
        }
        return normalized;
    }

    private String normalizeOrder(String order) {
        String normalized = order.trim().toLowerCase(Locale.ROOT);
        return switch (normalized) {
            case "asc", "desc" -> normalized;
            default -> throw new BadRequestException("Invalid order. Use 'asc' or 'desc'.");
        };
    }
}