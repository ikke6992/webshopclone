package nl.itvitae.webshop.product;

import lombok.AllArgsConstructor;
import nl.itvitae.webshop.category.Category;
import nl.itvitae.webshop.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/findall")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private record ProductData(String name, BigDecimal price) {}
    @PostMapping("/new/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable UUID id,
                                                 @RequestBody ProductData productData,
                                                 UriComponentsBuilder ucb) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (productData.name != null && productData.price != null && category.isPresent()) {
            Product product = productService.createProduct(productData.name, productData.price, category.get());
            URI locationOfNewProduct = ucb
                    .path("/api/v1/products/{id}")
                    .buildAndExpand(productService.getAllProducts().size())
                    .toUri();
            return ResponseEntity.created(locationOfNewProduct).body(product);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
