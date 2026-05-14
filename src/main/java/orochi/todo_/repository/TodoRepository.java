package orochi.todo_.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orochi.todo_.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Todo save(Todo todo);
    void deleteById(Integer id);

}
