package com.example.vendorservice.converter;

import com.example.vendorservice.dto.EventDto;
import com.example.vendorservice.model.Event;
import com.example.vendorservice.model.Vendor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventDtoConverter {
    public static List<EventDto> toDTOs(List<Event> events) {
        return events.stream().map(EventDtoConverter::toDto).collect(Collectors.toList());
    }

    // Convert an Event entity to an EventDto
    public static EventDto toDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getName(),
                event.getVendorId(),
                event.getEventDate(),
                event.getLocation(),
                event.getDescription()
        );
    }

    // Convert an EventDto to an Event entity
    public static Event toEntity(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.id());
        event.setName(eventDto.name());
        event.setVendorId(eventDto.vendorId());
        event.setEventDate(eventDto.eventDate());
        event.setLocation(eventDto.location());
        event.setDescription(eventDto.description());
        return event;
    }
}
