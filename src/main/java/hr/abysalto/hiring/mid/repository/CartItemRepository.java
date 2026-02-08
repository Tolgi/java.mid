package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
