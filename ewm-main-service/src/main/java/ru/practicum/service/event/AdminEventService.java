package ru.practicum.service.event;

import ru.practicum.dto.event.EventFilterParamsDto;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventService {

    List<EventFullDto> getEventsByAdmin(EventFilterParamsDto paramsDto);

    EventFullDto updateEventByAdmin(UpdateEventAdminRequest updateRequest, Long eventId);
}
