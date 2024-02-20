package com.sivleen.eventservice.service;

import com.sivleen.eventservice.dao.EventDao;
import com.sivleen.eventservice.expection.EventCreationException;
import com.sivleen.eventservice.expection.EventNotFoundException;
import com.sivleen.eventservice.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventDao eventDao;

    public ResponseEntity<String> createEvent(Event event) throws EventCreationException {
        if (event.getTotalSeats() <= 0) {
            throw new EventCreationException("Total seats must be greater than 0");
        }
        eventDao.save(event);
        return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Event>> getAllEvent() {
        return new ResponseEntity<List<Event>>(eventDao.findAll(), HttpStatus.OK);
    }

    public Event getEventById(Integer eventId) {

        return eventDao.findById(eventId).orElse(null);
    }

    public ResponseEntity<String> deleteEventById(Integer eventId) {
        eventDao.deleteById(eventId);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> updateEvent(Integer eventId, Map<String, Object> updates) throws EventNotFoundException {
        Optional<Event> eventOptional = eventDao.findById(eventId);

        if(eventOptional.isPresent()){
            Event event = eventOptional.get();

            updates.forEach((field, value) -> {
                switch (field) {
                    case "name":
                        event.setName((String) value);
                        break;
                    case "date":
                        event.setDate((LocalDate.parse((String) value)));
                        break;
                    case "location":
                        event.setLocation((String) value);
                        break;
                    case "totalSeats":
                        event.setTotalSeats((Integer) value);
                        break;
                    case "seatsBooked":
                        Integer seatsBooked = event.getSeatsBooked();
                        event.setSeatsBooked(seatsBooked + ((Integer) value));
                        break;
                    default:
                        break;
                }
            });

            eventDao.save(event);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        else{
            throw new EventNotFoundException("Event not found");
        }
    }

    public boolean checkAvailability(Integer eventId, Integer numSeats) {
        Event event = eventDao.findById(eventId).orElse(null);

        if (event != null) {
            int totalSeats = event.getTotalSeats();
            int bookedSeats = event.getSeatsBooked();
            int availableSeats = totalSeats - bookedSeats;
            return availableSeats >= numSeats;
        }

        return false;
    }
}
