package ru.practicum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.HitDto;
import ru.practicum.exception.BadRequestException;
import ru.practicum.mapper.HitMapper;
import ru.practicum.model.Stats;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Override
    @Transactional
    public void save(HitDto hitDto) {
        statsRepository.save(HitMapper.toHit(hitDto));
    }

    @Override
    public List<Stats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (end.isBefore(start)) {
            throw new BadRequestException("Wrong date-time range");
        }
        List<Stats> statsList;
        if (uris == null) {
            if (unique) {
                statsList = statsRepository.getUniqueStatsWithoutUris(start, end);
            } else {
                statsList = statsRepository.getNotUniqueStatsWithoutUris(start, end);
            }
        } else {
            if (unique) {
                statsList = statsRepository.getUniqueStats(start, end, uris);
            } else {
                statsList = statsRepository.getNotUniqueStats(start, end, uris);
            }
        }
        return statsList;
    }
}
