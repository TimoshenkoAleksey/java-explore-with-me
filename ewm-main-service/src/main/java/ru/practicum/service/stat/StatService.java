package ru.practicum.service.stat;

import ru.practicum.model.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatService {

    void addHit(HttpServletRequest request);

    Long getViews(Long eventId);

    void getViewsList(List<Event> events);
}
