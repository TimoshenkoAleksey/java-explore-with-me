package ru.practicum.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.HitDto;
import ru.practicum.model.Stats;
import ru.practicum.service.StatsService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatsController {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final StatsService statsService;

    @PostMapping("/hit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postHit(@RequestBody HitDto hitDto) {
        statsService.save(hitDto);
    }

    @GetMapping("/stats")
    public List<Stats> getStats(@RequestParam @DateTimeFormat(pattern = FORMAT) LocalDateTime start,
                                @RequestParam @DateTimeFormat(pattern = FORMAT) LocalDateTime end,
                                @RequestParam(required = false) List<String> uris,
                                @RequestParam(required = false, defaultValue = "false") Boolean unique) {
        return statsService.getStats(start, end, uris, unique);
    }

}
