package nl.itvitae.webshop.product;

import lombok.AllArgsConstructor;
import nl.itvitae.webshop.category.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(String name, BigDecimal price, Category category) {
        Product product = new Product(name, price, category);
        productRepository.save(product);
        return product;
    }
}
