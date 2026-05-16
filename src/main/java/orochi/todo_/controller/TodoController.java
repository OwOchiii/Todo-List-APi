package orochi.todo_.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import orochi.todo_.controller.request.TodoRequest;
import orochi.todo_.model.Todo;
import orochi.todo_.model.User;
import orochi.todo_.repository.UserRepository;
import orochi.todo_.service.todo.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final UserRepository userRepository;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        List<Todo> todos = todoService.getAllTodosById(user.getUserId());
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoRequest todoRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        String result = todoService.createTodo(todoRequest.getTitle(), todoRequest.getDescription(), user.getUserId());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Integer id, @RequestBody TodoRequest todoRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        // Assuming updateTodo updates regardless of user ownership in this basic implementation
        String result = todoService.updateTodo(todoRequest.getTitle(), todoRequest.getDescription(), id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        // Assuming deleteTodo deletes regardless of user ownership for now
        String result = todoService.deleteTodo(id);
        return ResponseEntity.ok(result);
    }
}


