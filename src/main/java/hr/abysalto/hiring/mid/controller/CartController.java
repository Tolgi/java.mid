package hr.abysalto.hiring.mid.controller;

import hr.abysalto.hiring.mid.dto.api.CartResponse;
import hr.abysalto.hiring.mid.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCurrentCart();
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(@PathVariable Long productId) {
        cartService.addItem(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
        return ResponseEntity.noContent().build();
    }
}
