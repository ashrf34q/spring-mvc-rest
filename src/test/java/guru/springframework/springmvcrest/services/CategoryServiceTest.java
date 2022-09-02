package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.springmvcrest.api.v1.model.CategoryDTO;
import guru.springframework.springmvcrest.domain.Category;
import guru.springframework.springmvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {
    public static final long ID = 1L;
    public static final String NAME = "Penny";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }
    @Test
    void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(4, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
    }
}