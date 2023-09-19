package ru.practicum.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public final class UserDto {
    private final Long id;
    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;
}
