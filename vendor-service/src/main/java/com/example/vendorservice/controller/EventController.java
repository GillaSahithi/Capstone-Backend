package com.example.vendorservice.controller;

import com.example.vendorservice.converter.EventDtoConverter;
import com.example.vendorservice.dto.EventDto;
import com.example.vendorservice.model.Event;
import com.example.vendorservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventDtoConverter converter;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        Event event = EventDtoConverter.toEntity(eventDto);
        Event savedEvent = eventService.createEvent(event);
        return ResponseEntity.ok(converter.toDto(savedEvent));
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> eventDtos = eventService.getAllEvents()
                .stream()
                .map(EventDtoConverter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<EventDto>> getEventsByVendorId(@PathVariable String vendorId) {
        List<EventDto> eventDtos = eventService.getAllEventsByVendorId(vendorId)
                .stream()
                .map(EventDtoConverter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable String id, @RequestBody EventDto eventDto) {
        Event event = EventDtoConverter.toEntity(eventDto);
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(EventDtoConverter.toDto(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted");
    }
}
