package nl.itvitae.webshop.category;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/findall")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Optional<Category> optionalCategory = categoryService.getCategoryByName(name);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private record CategoryData(String name) {}
    @PostMapping("/new")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryData categoryData,
                                                   UriComponentsBuilder ucb) {
        if (categoryData.name != null) {
            Category category = categoryService.createCategory(categoryData.name);
            URI locationOfNewCategory = ucb
                    .path("/api/v1/categories/{id}")
                    .buildAndExpand(categoryService.getAllCategories())
                    .toUri();
            return ResponseEntity.created(locationOfNewCategory).body(category);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
