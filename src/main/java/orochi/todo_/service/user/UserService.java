package orochi.todo_.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import orochi.todo_.model.User;
import orochi.todo_.repository.UserRepository;
import orochi.todo_.security.PasswordEncoderConfig;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoderConfig passwordEncoderConfig;

    private boolean isEmailTaken(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    private String createUser(String username, String email, String password){
        try {
            User user = new User();
            user.setUsername(username);
            if (isEmailTaken(email))
                throw new Exception("Email already taken");
            user.setEmail(email);
            user.setPassword(passwordEncoderConfig.passwordEncoder().encode(password));
            userRepository.save(user);
            return "User created successfully";
        }catch (Exception e)
        {
            return e.getMessage();
        }

    }

    private String login(String username,String email,String password){
        try {
            User user = userRepository.findUserByEmail(email);
            if (user == null)
                throw new Exception("User not found");
            if (!passwordEncoderConfig.passwordEncoder().matches(password, user.getPassword()))
                throw new Exception("Invalid password");
            return "Login successful";
        }catch (Exception e)
        {
            return e.getMessage();
        }
    }
}
