package hr.abysalto.hiring.mid.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite_products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FavoriteProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Long productId; // Product ID from API
}
