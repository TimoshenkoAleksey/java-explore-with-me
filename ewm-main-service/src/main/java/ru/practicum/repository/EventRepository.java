package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import ru.practicum.model.Event;
import ru.practicum.repository.customEvent.CustomEventRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository {

    List<Event> findByInitiatorId(Long initiatorId, Pageable pageable);

    Optional<Event> findByCategoryId(Long categoryId);
}
