package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.service.compilation.CompilationService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationController {
    private final CompilationService service;

    @GetMapping
    public List<CompilationDto> get(@RequestParam(required = false) Boolean pinned,
                                    @RequestParam(defaultValue = "0") @Positive Integer from,
                                    @RequestParam(defaultValue = "10")Integer size) {
        return service.getAll(pinned, from, size);
    }

    @GetMapping(value = "/{compId}")
    public CompilationDto getById(@PathVariable Long compId) {
        return service.getById(compId);
    }
}
