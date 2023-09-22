package ru.practicum.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@ToString
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private String status;
}
