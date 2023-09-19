package ru.practicum.service.compilation;

import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.dto.compilation.NewCompilationDto;
import ru.practicum.dto.compilation.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {

    List<CompilationDto> getAll(Boolean pinned, Integer from, Integer size);

    CompilationDto getById(Long compilationId);

    CompilationDto add(NewCompilationDto newCompilationDto);

    CompilationDto update(Long compilationId, UpdateCompilationRequest updateCompilationRequest);

    void delete(Long compilationId);
}
