package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.FavoriteProduct;
import hr.abysalto.hiring.mid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    boolean existsByUserAndExternalProductId(User user, Long externalProductId);

    Optional<FavoriteProduct> findByUserAndExternalProductId(User user, Long externalProductId);

    List<FavoriteProduct> findAllByUser(User user);
}
