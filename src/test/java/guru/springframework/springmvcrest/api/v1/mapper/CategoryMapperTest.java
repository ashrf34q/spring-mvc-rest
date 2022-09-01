package guru.springframework.springmvcrest.api.v1.mapper;

import guru.springframework.springmvcrest.api.v1.model.CategoryDTO;
import guru.springframework.springmvcrest.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final long ID = 1L;
    CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
       categoryMapper  = CategoryMapper.INSTANCE;
    }

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName("Joe");
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());

    }
}