package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.User;
import hu.nye.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Varga Dárius", "darovarga@gmail.com");
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> result = userService.findAll();
        assertEquals(1, result.size());
        assertEquals("Varga Dárius", result.get(0).getName());
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("darovarga@gmail.com", result.get().getEmail());
    }

    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<User> result = userService.findById(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.save(user);
        assertEquals("Varga Dárius", result.getName());
    }

    @Test
    void testDeleteById() {
        userService.deleteById(1L);
        verify(userRepository).deleteById(1L);
    }
}