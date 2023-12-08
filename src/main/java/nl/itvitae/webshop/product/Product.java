package nl.itvitae.webshop.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.webshop.category.Category;
import nl.itvitae.webshop.review.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private BigDecimal price;


    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Category category;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getScore() {
        BigDecimal score = BigDecimal.valueOf(0.0);
        for (Review review : reviews) {
            score = score.add(BigDecimal.valueOf(review.getScore()));
        }
        return score.divide(BigDecimal.valueOf(Math.max(reviews.size(), 1)), 1, RoundingMode.HALF_UP);
    }

}
