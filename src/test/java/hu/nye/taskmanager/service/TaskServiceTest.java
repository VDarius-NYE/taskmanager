package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.Category;
import hu.nye.taskmanager.model.Task;
import hu.nye.taskmanager.model.User;
import hu.nye.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        User user = new User(1L, "Varga Dárius", "darovarga@gmail.com");
        Category category = new Category(1L, "Munka", "#ff0000");
        task = new Task(
                1L,
                "Teszt Feladat",
                "Leírás",
                Task.Status.TODO,
                LocalDate.of(2026, 12, 31),
                user,
                category
        );
    }

    @Test
    void testFindAll() {
        when(taskRepository.findAll()).thenReturn(List.of(task));
        List<Task> result = taskService.findAll();
        assertEquals(1, result.size());
        assertEquals("Teszt Feladat", result.get(0).getTitle());
    }

    @Test
    void testFindById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(Task.Status.TODO, result.get().getStatus());
    }

    @Test
    void testFindByIdNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Task> result = taskService.findById(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.save(task);
        assertEquals("Teszt Feladat", result.getTitle());
    }

    @Test
    void testDeleteById() {
        taskService.deleteById(1L);
        verify(taskRepository).deleteById(1L);
    }
}