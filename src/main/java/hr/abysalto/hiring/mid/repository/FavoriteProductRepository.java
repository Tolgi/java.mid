package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.FavoriteProduct;
import hr.abysalto.hiring.mid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    boolean existsByUserAndProductId(User user, Long externalProductId);

    Optional<FavoriteProduct> findByUserAndProductId(User user, Long externalProductId);

    List<FavoriteProduct> findAllByUser(User user);
}
