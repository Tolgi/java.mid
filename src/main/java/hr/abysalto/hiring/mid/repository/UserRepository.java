package hr.abysalto.hiring.mid.repository;

import hr.abysalto.hiring.mid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
