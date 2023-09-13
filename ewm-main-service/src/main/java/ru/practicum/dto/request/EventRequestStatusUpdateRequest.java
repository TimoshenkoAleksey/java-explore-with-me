package ru.practicum.dto.request;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private String status;
}
