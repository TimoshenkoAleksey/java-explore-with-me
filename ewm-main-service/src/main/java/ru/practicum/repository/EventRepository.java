package ru.practicum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.Event;
import ru.practicum.repository.custom_event.CustomEventRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository {

    List<Event> findByInitiatorId(Long initiatorId, Pageable pageable);

    Optional<Event> findByCategoryId(Long categoryId);
}
