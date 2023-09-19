package ru.practicum.dto.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class NewCategoryDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
