package nl.itvitae.webshop;

import nl.itvitae.webshop.category.Category;
import nl.itvitae.webshop.category.CategoryRepository;
import nl.itvitae.webshop.product.Product;
import nl.itvitae.webshop.product.ProductRepository;
import nl.itvitae.webshop.review.Review;
import nl.itvitae.webshop.review.ReviewRepository;
import nl.itvitae.webshop.user.User;
import nl.itvitae.webshop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    public void seed() {
        Category electronics = new Category("Devices");
        categoryRepository.save(electronics);
        Category media = new Category("Media");
        categoryRepository.save(media);
        Category books = new Category("Books");
        categoryRepository.save(books);
        Category toys = new Category("Toys");
        categoryRepository.save(toys);

        Product laptop = new Product("Laptop", BigDecimal.valueOf(999.99), electronics);
        productRepository.save(laptop);
        Product tv = new Product("TV", BigDecimal.valueOf(1499.99), electronics);
        productRepository.save(tv);
        Product dvd = new Product("DVD", BigDecimal.valueOf(19.99), media);
        productRepository.save(dvd);
        Product game = new Product("PCGame", BigDecimal.valueOf(54.99), media);
        productRepository.save(game);
        Product book = new Product("Book", BigDecimal.valueOf(14.99), books);
        productRepository.save(book);
        Product eBook = new Product("eBook", BigDecimal.valueOf(11.99), books);
        productRepository.save(eBook);
        Product stuffedAnimal = new Product("Stuffed Animal", BigDecimal.valueOf(9.99), toys);
        productRepository.save(stuffedAnimal);
        Product lego = new Product("lego", BigDecimal.valueOf(29.99), toys);
        productRepository.save(lego);

        User tony = new User("Tony");
        userRepository.save(tony);
        User ezekiel = new User("Ezekiel");
        userRepository.save(ezekiel);
        User everyone = new User("Everyone");
        userRepository.save(everyone);

        Review dvdReview = new Review(1, "Archaic Object", tony, dvd);
        reviewRepository.save(dvdReview);
        Review stuffedAnimalReview = new Review(5, "CYUUUUUUUUUUTE", tony, stuffedAnimal);
        reviewRepository.save(stuffedAnimalReview);
        Review legoReview = new Review(4, "FUCK YOU TONY!", ezekiel, lego);
        reviewRepository.save(legoReview);
        Review legoReview1 = new Review(3, "FUCK YOU EZEKIEL!", tony, lego);
        reviewRepository.save(legoReview1);
        Review legoReview2 = new Review(4, "Lego rules", everyone, lego);
        reviewRepository.save(legoReview2);

    }
}
