package ru.practicum.dto.category;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public final class CategoryDto {
    private final Long id;
    @NotBlank
    @Length(min = 1, max = 50)
    private final String name;
}
