package guru.springframework.springmvcrest.api.v1.mapper;

import guru.springframework.springmvcrest.api.v1.model.CategoryDTO;
import guru.springframework.springmvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
