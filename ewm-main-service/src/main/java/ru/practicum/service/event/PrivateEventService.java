package ru.practicum.service.event;

import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.dto.request.ParticipationRequestDto;

import java.util.List;

public interface PrivateEventService {

    List<EventShortDto> getEventsByPrivate(Long userId,Integer from, Integer size);

    EventFullDto createByPrivate(NewEventDto newEventDto, Long userId);

    EventFullDto getEventByPrivate(Long userId, Long eventId);

    EventFullDto updateByPrivate(UpdateEventUserRequest updateEventUserRequest, Long userId, Long eventId);

    List<ParticipationRequestDto> getRequestsByPrivate(Long userId, Long eventId);

    EventRequestStatusUpdateResult updateEventStatusByPrivate(EventRequestStatusUpdateRequest updateRequest, Long userId,
                                                              Long eventId);
}
