package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.service.category.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class PublicCategoryController {
    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> get(@RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                 @RequestParam(defaultValue = "10") @Positive Integer size) {
        return service.getAll(from, size);
    }

    @GetMapping(value = "/{catId}")
    public CategoryDto get(@PathVariable Long catId) {
        return service.getById(catId);
    }
}
