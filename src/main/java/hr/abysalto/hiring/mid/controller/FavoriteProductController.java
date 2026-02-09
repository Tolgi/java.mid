package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.configuration.SwaggerConfig;
import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.service.FavoriteProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Favorites", description = "Manage favorite products for the authenticated user.")
@SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteProductController {

    private final FavoriteProductService favoriteService;

    @Operation(summary = "Get favorite products", description = "Returns all favorite products of the currently authenticated user.")
    @PostMapping("/{productId}")
    public ResponseEntity<Void> add(@PathVariable Long productId) {
        favoriteService.addFavorite(productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove product from favorites", description = " Removes a product from the authenticated user's favorites list.")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId) {
        favoriteService.removeFavorite(productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add product to favorites", description = "Adds a product to the authenticated user's favorites list.")
    @GetMapping
    public List<ProductResponse> list() {
        return favoriteService.listFavorites();
    }
}
