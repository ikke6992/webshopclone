package nl.itvitae.webshop.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/findall")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private record UserData(String name) {
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody UserData userData,
            UriComponentsBuilder ucb) {
        if (userData.name != null) {
            User user = userService.createUser(new User(userData.name));
            URI locationOfNewUser = ucb
                    .path("/api/v1/users/{id}")
                    .buildAndExpand(userService.getAllUsers())
                    .toUri();
            return ResponseEntity.created(locationOfNewUser).body(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
