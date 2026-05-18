package hu.nye.taskmanager.service;

import hu.nye.taskmanager.model.Category;
import hu.nye.taskmanager.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "Munka", "#ff0000");
    }

    @Test
    void testFindAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        List<Category> result = categoryService.findAll();
        assertEquals(1, result.size());
        assertEquals("Munka", result.get(0).getName());
    }

    @Test
    void testFindById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Munka", result.get().getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Category> result = categoryService.findById(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(categoryRepository.save(category)).thenReturn(category);
        Category result = categoryService.save(category);
        assertEquals("Munka", result.getName());
    }

    @Test
    void testDeleteById() {
        categoryService.deleteById(1L);
        verify(categoryRepository).deleteById(1L);
    }
}
