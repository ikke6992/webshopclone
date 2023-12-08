package nl.itvitae.webshop.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.webshop.product.Product;
import nl.itvitae.webshop.user.User;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    private Long id;

    private int score;

    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Review(int score, String description, User reviewer, Product product) {
        this.score = score;
        this.description = description;
        this.reviewer = reviewer;
        this.product = product;
    }

    public String getUser() {
        return reviewer.getUsername();
    }

}
