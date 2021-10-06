package ua.sinitsyn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sinitsyn.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
