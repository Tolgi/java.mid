package hr.abysalto.hiring.mid.service;

import hr.abysalto.hiring.mid.domain.Cart;
import hr.abysalto.hiring.mid.domain.CartItem;
import hr.abysalto.hiring.mid.domain.User;
import hr.abysalto.hiring.mid.dto.api.CartItemResponse;
import hr.abysalto.hiring.mid.dto.api.CartResponse;
import hr.abysalto.hiring.mid.exception.BadRequestException;
import hr.abysalto.hiring.mid.repository.CartItemRepository;
import hr.abysalto.hiring.mid.repository.CartRepository;
import hr.abysalto.hiring.mid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    @Transactional
    public void addItem(Long productId) {
        if (productId == null || productId <= 0) {
            throw new BadRequestException("Invalid product id");
        }

        User user = currentUserEntity();
        productService.getProductById(productId);
        Cart cart = getOrCreateActiveCart(user);
        CartItem item = cartItemRepository.findByCartAndProductId(cart, productId)
                .orElseGet(() -> CartItem.builder()
                        .cart(cart)
                        .productId(productId)
                        .quantity(0)
                        .build());
        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

    @Transactional
    public void removeItem(Long productId) {
        if (productId == null || productId <= 0) {
            throw new BadRequestException("Invalid product id");
        }

        User user = currentUserEntity();
        Cart cart = getOrCreateActiveCart(user);
        cartItemRepository.findByCartAndProductId(cart, productId).ifPresent(item -> {
            int newQty = item.getQuantity() - 1;
            if (newQty <= 0) {
                cartItemRepository.delete(item);
            } else {
                item.setQuantity(newQty);
                cartItemRepository.save(item);
            }
        });
    }

    @Transactional(readOnly = true)
    public CartResponse getCurrentCart() {
        User user = currentUserEntity();
        Cart cart = cartRepository.findByUserAndStatus(user, Cart.Status.ACTIVE)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).status(Cart.Status.ACTIVE).build()));
        List<CartItemResponse> items = cartItemRepository.findAllByCart(cart).stream()
                .sorted(Comparator.comparing(CartItem::getId))
                .map(ci -> new CartItemResponse(
                        ci.getProductId(),
                        ci.getQuantity(),
                        productService.getProductById(ci.getProductId())
                ))
                .toList();

        return new CartResponse(cart.getId(), items);
    }

    private Cart getOrCreateActiveCart(User user) {
        return cartRepository.findByUserAndStatus(user, Cart.Status.ACTIVE)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder().user(user).status(Cart.Status.ACTIVE).build()
                ));
    }

    private User currentUserEntity() {
        Long userId = AuthService.id();
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
