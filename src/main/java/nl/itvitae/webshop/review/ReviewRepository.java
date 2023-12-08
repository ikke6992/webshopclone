package nl.itvitae.webshop.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ReviewRepository extends JpaRepository<Review, UUID>{
    
}
