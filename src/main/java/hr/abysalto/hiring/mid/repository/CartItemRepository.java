package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.Cart;
import hr.abysalto.hiring.mid.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndExternalProductId(Cart cart, Long externalProductId);

    List<CartItem> findAllByCart(Cart cart);
}
