package hr.abysalto.hiring.mid.service;

import hr.abysalto.hiring.mid.domain.FavoriteProduct;
import hr.abysalto.hiring.mid.domain.User;
import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.exception.BadRequestException;
import hr.abysalto.hiring.mid.repository.FavoriteProductRepository;
import hr.abysalto.hiring.mid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteProductService {

    private final FavoriteProductRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductService productService; // used to validate + enrich

    @Transactional
    public void addFavorite(Long productId) {
        if (productId == null || productId <= 0) {
            throw new BadRequestException("Invalid product id");
        }

        User user = currentUserEntity();
        productService.getProductById(productId);
        if (favoriteRepository.existsByUserAndProductId(user, productId)) {
            return;
        }

        FavoriteProduct fav = FavoriteProduct.builder()
                .user(user)
                .productId(productId)
                .build();

        favoriteRepository.save(fav);
    }

    @Transactional
    public void removeFavorite(Long productId) {
        if (productId == null || productId <= 0) {
            throw new BadRequestException("Invalid product id");
        }

        User user = currentUserEntity();
        favoriteRepository.findByUserAndProductId(user, productId)
                .ifPresent(favoriteRepository::delete);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> listFavorites() {
        User user = currentUserEntity();
        return favoriteRepository.findAllByUser(user).stream()
                .map(FavoriteProduct::getProductId)
                .distinct()
                .map(productService::getProductById)
                .toList();
    }

    private User currentUserEntity() {
        Long userId = AuthService.id();
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
