package ru.practicum.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.practicum.enums.StateActionAdmin;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest extends UpdateEventRequest {
    private StateActionAdmin stateAction;
}
