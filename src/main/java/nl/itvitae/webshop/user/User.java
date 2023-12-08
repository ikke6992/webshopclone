package nl.itvitae.webshop.user;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.webshop.review.Review;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "reviewer")
    private Set<Review> reviews = new HashSet<Review>();

    public User(String username) {
        this.username = username;
    }
}
