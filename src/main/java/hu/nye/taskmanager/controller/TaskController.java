package hu.nye.taskmanager.controller;

import hu.nye.taskmanager.model.Task;
import hu.nye.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final  TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable final Long id) {
        return taskService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task create(@RequestBody final Task task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable final Long id, @RequestBody final Task task) {
        return taskService.findById(id).map(existing -> {
            existing.setTitle(task.getTitle());
            existing.setDesc(task.getDesc());
            existing.setStatus(task.getStatus());
            existing.setDueDate(task.getDueDate());
            existing.setUser(task.getUser());
            existing.setCategory(task.getCategory());
            return ResponseEntity.ok(taskService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public  ResponseEntity<Void> delete(@PathVariable final Long id) {
        taskService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
