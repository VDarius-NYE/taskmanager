package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.Category;
import hu.nye.taskmanager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(final Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(final Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(final Long id) {
        categoryRepository.deleteById(id);
    }
}
