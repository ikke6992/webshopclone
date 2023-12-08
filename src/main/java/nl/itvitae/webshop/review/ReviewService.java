package nl.itvitae.webshop.review;

import lombok.AllArgsConstructor;
import nl.itvitae.webshop.product.Product;
import nl.itvitae.webshop.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(UUID id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(int score, String description, User user, Product product) {
        Review review = new Review(score, description, user, product);
        reviewRepository.save(review);
        return review;
    }
}
