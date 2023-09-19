package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.service.event.PrivateEventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class PrivateEventController {
    private final PrivateEventService service;

    @PostMapping(value = "/{userId}/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto add(@PathVariable Long userId,
                            @Valid @RequestBody NewEventDto newEventDto) {
        return service.createByPrivate(newEventDto, userId);
    }

    @GetMapping(value = "/{userId}/events")
    public List<EventShortDto> getAll(@PathVariable Long userId,
                                      @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                      @RequestParam(defaultValue = "10") @Positive Integer size) {
        return service.getEventsByPrivate(userId, from, size);
    }

    @GetMapping(value = "/{userId}/events/{eventId}")
    public EventFullDto getEvent(@PathVariable Long userId,
                                 @PathVariable Long eventId) {
        return service.getEventByPrivate(userId, eventId);
    }

    @PatchMapping(value = "/{userId}/events/{eventId}")
    public EventFullDto update(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @Valid @RequestBody UpdateEventUserRequest updateRequest) {
        return service.updateByPrivate(updateRequest, userId, eventId);
    }

    @GetMapping(value = "/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getRequests(@PathVariable Long userId,
                                                     @PathVariable Long eventId) {
        return service.getRequestsByPrivate(userId, eventId);
    }

    @PatchMapping(value = "/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult update(@PathVariable Long userId,
                                                 @PathVariable Long eventId,
                                                 @RequestBody EventRequestStatusUpdateRequest updateRequest) {
        return service.updateStatusByPrivate(updateRequest, userId, eventId);
    }
}
