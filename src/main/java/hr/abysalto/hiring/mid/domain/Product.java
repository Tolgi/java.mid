package hr.abysalto.hiring.mid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    private Long id; // ID from DummyJSON API

    private String title;
    private String brand;
    private String description;
    private double price;
    private String thumbnail;
    private LocalDateTime lastAccessed;
}
