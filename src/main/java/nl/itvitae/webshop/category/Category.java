package nl.itvitae.webshop.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.webshop.product.Product;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(String name) {
        this.name = name;
    }
}
