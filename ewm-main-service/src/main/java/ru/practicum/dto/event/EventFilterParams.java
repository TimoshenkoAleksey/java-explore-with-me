package ru.practicum.dto.event;

import lombok.*;
import ru.practicum.enums.EventSort;
import ru.practicum.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventFilterParams {
    private List<Long> ids;
    private List<EventStatus> states;
    private List<Long> categories;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private Integer from;
    private Integer size;
    private String text;
    private Boolean paid;
    private Boolean onlyAvailable;
    private EventSort sort;
}
