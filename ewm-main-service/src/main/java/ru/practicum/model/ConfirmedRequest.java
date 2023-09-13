package ru.practicum.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ConfirmedRequest {
    private Long count;
    private Long eventId;
}
