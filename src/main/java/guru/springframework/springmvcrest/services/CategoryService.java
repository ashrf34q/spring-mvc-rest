package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories();

    public CategoryDTO getCategoryByName(String name);
}
