package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.service.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteProductController {

    private final FavoriteProductService favoriteService;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> add(@PathVariable Long productId) {
        favoriteService.addFavorite(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId) {
        favoriteService.removeFavorite(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ProductResponse> list() {
        return favoriteService.listFavorites();
    }
}
