package ru.practicum.dto.event;

import lombok.*;
import ru.practicum.enums.EventSort;
import ru.practicum.enums.EventStatus;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventFilterParamsDto {
    private List<Long> ids = List.of();
    private List<EventStatus> states = List.of();
    private List<Long> categories = List.of();
    private String rangeStart;
    private String rangeEnd;
    private Integer from = 0;
    private Integer size = 10;
    private String text;
    private Boolean paid;
    private Boolean onlyAvailable = Boolean.FALSE;
    private EventSort sort = EventSort.EVENT_DATE;
}
