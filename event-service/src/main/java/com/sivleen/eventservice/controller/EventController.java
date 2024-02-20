package com.sivleen.eventservice.controller;

import com.sivleen.eventservice.expection.EventCreationException;
import com.sivleen.eventservice.expection.EventNotFoundException;
import com.sivleen.eventservice.model.Event;
import com.sivleen.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        try {
            return eventService.createEvent(event);
        } catch (EventCreationException e) {
            return new ResponseEntity<String>("Event creation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvent() {
        return eventService.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Integer eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return new ResponseEntity<Event>(event,HttpStatus.OK);
        } else {
            return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable Integer eventId, @RequestBody Map<String,Object> updates) {
        try {
            return eventService.updateEvent(eventId, updates);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<String> ("Event not found" ,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer eventId) {

        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return eventService.deleteEventById(eventId);
        } else {
            return new ResponseEntity<>("Event doesn't exist",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{eventId}/checkAvailability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Integer eventId, @RequestParam Integer numSeats) {
        boolean isAvailable = eventService.checkAvailability(eventId, numSeats);
        return ResponseEntity.ok(isAvailable);
    }
}
