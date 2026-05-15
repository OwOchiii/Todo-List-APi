package orochi.todo_.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orochi.todo_.model.User;
import orochi.todo_.service.user.UserService;

@RestController
@RequestMapping ("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.createUser(user.getUsername(), user.getEmail(), user.getPassword());
        if (result.equals("User created successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String result = userService.login(user.getUsername(), user.getEmail(), user.getPassword());
        if (result.equals("Login successful")) {
            return ResponseEntity.ok(result);
        }
    }
}
