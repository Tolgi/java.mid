package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.Cart;
import hr.abysalto.hiring.mid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
}
