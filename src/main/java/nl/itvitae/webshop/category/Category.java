package nl.itvitae.webshop.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.webshop.product.Product;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(String name) {
        this.name = name;
    }
}
