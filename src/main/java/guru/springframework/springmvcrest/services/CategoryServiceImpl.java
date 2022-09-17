package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.springmvcrest.api.v1.model.CategoryDTO;
import guru.springframework.springmvcrest.domain.Category;
import guru.springframework.springmvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        if(categoryRepository.findByName(name) != null) {
            Category category = categoryRepository.findByName(name);

            return categoryMapper.categoryToCategoryDTO(category);
        }
        else {
            throw new ResourceNotFoundException();
        }

    }
}
