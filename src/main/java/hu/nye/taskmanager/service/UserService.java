package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.User;
import hu.nye.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }
}
