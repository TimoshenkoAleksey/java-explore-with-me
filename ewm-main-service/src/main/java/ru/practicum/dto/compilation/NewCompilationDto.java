package ru.practicum.dto.compilation;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCompilationDto {
    @NotBlank
    @Length(min = 1, max = 50)
    private String title;
    private Set<Long> events = new HashSet<>();
    private boolean pinned = Boolean.FALSE;
}
