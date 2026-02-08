package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
