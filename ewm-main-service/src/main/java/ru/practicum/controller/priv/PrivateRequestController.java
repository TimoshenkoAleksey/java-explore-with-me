package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.service.request.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class PrivateRequestController {
    private final RequestService service;

    @GetMapping(value = "/{userId}/requests")
    public List<ParticipationRequestDto> getAll(@PathVariable Long userId) {
        return service.getAll(userId);
    }

    @PostMapping(value = "/{userId}/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto add(@PathVariable Long userId,
                                       @RequestParam Long eventId) {
        return service.add(userId, eventId);
    }

    @PatchMapping(value = "/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable Long userId,
                                          @PathVariable Long requestId) {
        return service.cancel(userId, requestId);
    }

}
