package ru.practicum.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.practicum.enums.EventStatus;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @CreationTimestamp
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    @Valid
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private Boolean paid;
    private Long participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EventStatus status = EventStatus.PENDING;
    private String title;
    @Transient
    private Long views;
    @Transient
    private Long confirmedRequests;
}
