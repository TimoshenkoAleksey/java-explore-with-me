package ru.practicum.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.enums.EventState;
import ru.practicum.enums.RequestStatus;
import ru.practicum.exception.exeptions.ConflictException;
import ru.practicum.exception.exeptions.NotFoundException;
import ru.practicum.model.Event;
import ru.practicum.repository.EventRepository;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.mapper.ParticipationRequestMapper;
import ru.practicum.model.ParticipationRequest;
import ru.practicum.repository.RequestRepository;
import ru.practicum.model.User;
import ru.practicum.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipationRequestMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> getAll(Long userId) {
        getUserIfExists(userId);
        List<ParticipationRequest> requests = requestRepository.findByRequesterId(userId);
        return mapper.toRequestDtoList(requests);
    }

    @Override
    @Transactional
    public ParticipationRequestDto add(Long userId, Long eventId) {
        if (requestRepository.findFirst1ByEventIdAndRequesterId(eventId, userId).isPresent()) {
            throw new ConflictException("Participation request already exists.");
        }
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found."));
        if (userId.equals(event.getInitiator().getId())) {
            throw new ConflictException("Event owner not allowed to create request to his own event.");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Invalid event status.");
        }
        if (event.getParticipantLimit() > 0) {
            Long participants = requestRepository.countByEventIdAndStatus(event.getId(), RequestStatus.CONFIRMED);
            Long limit = event.getParticipantLimit();
            if (participants >= limit) {
                throw new ConflictException("Participants limit is reached.");
            }
        }
        ParticipationRequest request = completeNewRequest(userId, event);
        return mapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancel(Long userId, Long requestId) {
        getUserIfExists(userId);
        ParticipationRequest request = requestRepository.findById(requestId).orElseThrow(() ->
                new NotFoundException("Participation request not found."));
        request.setStatus(RequestStatus.CANCELED);
        return mapper.toRequestDto(requestRepository.save(request));
    }

    private ParticipationRequest completeNewRequest(Long userId, Event event) {
        User user = getUserIfExists(userId);
        boolean needConfirmation = event.getRequestModeration();
        boolean hasParticipantsLimit = event.getParticipantLimit() != 0;
        RequestStatus status = needConfirmation && hasParticipantsLimit ? RequestStatus.PENDING : RequestStatus.CONFIRMED;
        return ParticipationRequest.builder()
                .requester(user)
                .status(status)
                .event(event)
                .build();
    }

    private User getUserIfExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found or unavailable."));
    }
}
