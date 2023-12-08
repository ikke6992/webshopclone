package nl.itvitae.webshop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository extends JpaRepository<User, UUID> {
    
}
