package ru.practicum.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.practicum.enums.StateActionUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    private StateActionUser stateAction;
}
