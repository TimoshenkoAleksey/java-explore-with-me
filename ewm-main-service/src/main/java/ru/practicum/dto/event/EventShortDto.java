package ru.practicum.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class EventShortDto extends EventDto{

}
