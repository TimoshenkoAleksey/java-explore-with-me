package ru.practicum.service.category;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.error.exeption.ConflictException;
import ru.practicum.error.exeption.NotFoundException;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.model.Category;
import ru.practicum.repository.CategoryRepository;
import ru.practicum.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public List<CategoryDto> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper::toCategoryDto).getContent();
    }

    @Override
    @Transactional
    public CategoryDto getById(Long categoryId) {
        Category category = getCategoryIfExists(categoryId);
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    @Transactional
    public CategoryDto add(NewCategoryDto newCategoryDto) {
        Category category = categoryRepository.save(categoryMapper.toCategory(newCategoryDto));
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    @Transactional
    public CategoryDto update(CategoryDto categoryDto, Long categoryId) {
        Category category = getCategoryIfExists(categoryId);
        String newName = categoryDto.getName();
        String existingName = category.getName();
        category.setName(StringUtils.defaultIfBlank(newName, existingName));
        return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long categoryId) {
        Category category = getCategoryIfExists(categoryId);
        if (eventRepository.findByCategoryId(categoryId).isPresent()) {
            throw new ConflictException("Category is connected with events and could be deleted");
        }
        categoryRepository.delete(category);
    }

    private Category getCategoryIfExists(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
