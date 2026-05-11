package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.Task;
import hu.nye.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(final Long id) {
        return taskRepository.findById(id);
    }

    public Task save(final Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(final Long id) {
        taskRepository.deleteById(id);
    }
}
