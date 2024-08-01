package de.ait.usermanagment.controller;

import de.ait.usermanagment.exceptions.UserAlreadyExistsException;
import de.ait.usermanagment.exceptions.UserIsNotExistsException;
import de.ait.usermanagment.model.User;
import de.ait.usermanagment.repository.UserRepository;
import de.ait.usermanagment.service.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserServiceInterface userService;


    @Operation(summary = "Create new user (Register)")
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("update/{id}") // Anotācija norāda, ka šī metode atbildēs uz PUT pieprasījumiem uz /api/users/{id}.
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user); // Izsauc servisa metodi, lai atjauninātu lietotāju ar norādīto informāciju.
            return ResponseEntity.ok(updatedUser); // Atgriež atjaunināto lietotāju un HTTP statusu 200 (OK).
        } catch (UserIsNotExistsException e) {
            return ResponseEntity.notFound().build(); // Ja lietotājs ar norādīto ID netiek atrasts, atgriež HTTP statusu 404 (Not Found).
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID: " + id +  " deleted successfully");
        } catch (UserIsNotExistsException e) {
            return ResponseEntity.ok("User with ID: " + id +  " not found");
        }
    }
}