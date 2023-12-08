package nl.itvitae.webshop.review;

import lombok.AllArgsConstructor;
import nl.itvitae.webshop.product.Product;
import nl.itvitae.webshop.product.ProductService;
import nl.itvitae.webshop.user.User;
import nl.itvitae.webshop.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Review>> getAll() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable long id) {
        Optional<Review> optionalReview = reviewService.getReviewById(id);
        if (optionalReview.isPresent()) {
            return ResponseEntity.ok(optionalReview.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private record ReviewData(int score, String description) {}
    @PostMapping("/new/{user_id}/{product_id}")
    public ResponseEntity<Review> createReview(@RequestBody ReviewData reviewData,
                                             @PathVariable(value="user_id") long userId,
                                             @PathVariable(value="product_id") long productId,
                                             UriComponentsBuilder ucb) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Product> product = productService.getProductById(productId);
        if (reviewData.score > 0 && reviewData.score <= 5 && reviewData.description != null &&
                user.isPresent() && product.isPresent()) {
            Review review = reviewService
                    .createReview(reviewData.score, reviewData.description, user.get(), product.get());
            URI locationOfNewReview = ucb
                    .path("/api/v1/reviews/id/{id}")
                    .buildAndExpand(reviewService.getAllReviews().size())
                    .toUri();
            return ResponseEntity.created(locationOfNewReview).body(review);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
