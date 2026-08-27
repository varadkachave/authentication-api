package varad.in.spring_boot_jwt_authentication_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import varad.in.spring_boot_jwt_authentication_api.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
