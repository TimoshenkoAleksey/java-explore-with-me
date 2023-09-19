package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.model.ParticipationRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationRequestMapper {
    @Mapping(target = "event", source = "event.id")
    @Mapping(target = "created", expression = "java(ru.practicum.utils.ExploreDateTimeFormatter.localDateTimeToString(request.getCreated()))")
    @Mapping(target = "requester", source = "requester.id")
    @Mapping(target = "status", source = "status")
    ParticipationRequestDto toRequestDto(ParticipationRequest request);

    List<ParticipationRequestDto> toRequestDtoList(List<ParticipationRequest> requests);
}
