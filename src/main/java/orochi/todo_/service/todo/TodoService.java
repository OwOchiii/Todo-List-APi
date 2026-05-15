package orochi.todo_.service.todo;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import orochi.todo_.model.Todo;
import orochi.todo_.repository.TodoRepository;
import orochi.todo_.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public List<Todo> getAllTodosById(Integer id) {
        try
        {
            List<Todo> todos = todoRepository.findAllByUser_UserId(id);
            if (todos.isEmpty())
                throw new Exception("No todos found");
            return todos;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public String createTodo(String title, String description, Integer userId) {
        try
        {
            Todo todo = new Todo();
            if (userRepository.getUserByUserId(userId) == null)
            {
                throw new Exception("User not found");
            }
            todo.setUser(userRepository.getUserByUserId(userId));
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setCompleted(false);
            todoRepository.save(todo);
            return "Todo created successfully";
        }
        catch (Exception e)
            {
            return "Error" + e.getMessage();
            }
    }

    public String deleteTodo(Integer id) {
        try
        {
            if (todoRepository.getTodosById(id) == null)
                throw new Exception("Todo not found");
            todoRepository.deleteById(id);
            return "Todo deleted successfully";
        }
        catch (Exception e)
        {
            return "Error" + e.getMessage();
        }
    }

    public String updateTodo(String title, String description, Integer id) {
        try{
            Todo todo = new Todo();
            if (todoRepository.getTodosById(id) == null)
                throw new Exception("Todo not found");
            todo = todoRepository.getTodosById(id);
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setCompleted(false);
            todoRepository.save(todo);
            return "Todo update successfully";
        }
        catch (Exception e){
            return "Error" + e.getMessage();
        }
    }
}
