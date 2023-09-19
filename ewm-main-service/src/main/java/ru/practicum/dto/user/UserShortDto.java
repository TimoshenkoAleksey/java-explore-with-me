package ru.practicum.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserShortDto {
    private Long id;
    private String name;
}
