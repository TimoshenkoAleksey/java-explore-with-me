package ru.practicum.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
    private List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
}
