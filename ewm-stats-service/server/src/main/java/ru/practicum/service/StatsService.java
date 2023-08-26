package ru.practicum.service;

import org.springframework.data.jpa.repository.Query;
import ru.practicum.HitDto;
import ru.practicum.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    void save(HitDto hitDto);

    List<Stats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

}
