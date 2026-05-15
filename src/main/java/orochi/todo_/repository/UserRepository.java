package orochi.todo_.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orochi.todo_.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findUserByEmail(String email);
    User save(User user);
    User getUserByUserId(Integer userId);
}
