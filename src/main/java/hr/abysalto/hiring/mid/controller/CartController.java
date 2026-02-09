package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.dto.api.CartResponse;
import hr.abysalto.hiring.mid.service.CartService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import hr.abysalto.hiring.mid.configuration.SwaggerConfig;

@Tag(name = "Cart", description = "Manage the authenticated user's cart.")
@SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Get current cart", description = "Returns the authenticated user's cart with product details.")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Current cart")
    public CartResponse getCart() {
        return cartService.getCurrentCart();
    }

    @Operation(summary = "Add product to cart", description = "Increments quantity by 1 (creates item if missing).")
    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(
            @Parameter(description = "Product ID from DummyJSON.", example = "1")
            @PathVariable Long productId) {
        cartService.addItem(productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove product from cart", description = "Decrements quantity by 1 (removes item if quantity reaches 0).")
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(
            @Parameter(description = "Product ID from DummyJSON.", example = "1")
            @PathVariable Long productId
    ) {
        cartService.removeItem(productId);
        return ResponseEntity.noContent().build();
    }
}
