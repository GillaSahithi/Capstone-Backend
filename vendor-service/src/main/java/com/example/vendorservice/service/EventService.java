package com.example.vendorservice.service;

import com.example.vendorservice.exception.EventNotFoundException;
import com.example.vendorservice.model.Event;
import com.example.vendorservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsByVendorId(String vendorId) {
        List<Event> events = eventRepository.findByVendorId(vendorId);
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }
        return events;
    }

    public Event updateEvent(String eventId, Event eventDetails) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));

        event.setName(eventDetails.getName());
        event.setEventDate(eventDetails.getEventDate());
        event.setLocation(eventDetails.getLocation());
        event.setDescription(eventDetails.getDescription());

        return eventRepository.save(event);
    }

    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

}

