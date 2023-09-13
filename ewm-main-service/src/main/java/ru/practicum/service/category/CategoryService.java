package ru.practicum.service.category;

import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll(Integer from, Integer size);

    CategoryDto getById(Long categoryId);

    CategoryDto add(NewCategoryDto newCategoryDto);

    CategoryDto update(CategoryDto categoryDto, Long categoryId);

    void delete(Long categoryId);
}
