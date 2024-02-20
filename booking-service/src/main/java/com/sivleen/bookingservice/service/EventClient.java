package com.sivleen.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "EVENT-SERVICE")
public interface EventClient {
    @PutMapping("/events/{eventId}")
    void updateEventAvailability(@PathVariable Integer eventId, @RequestBody Map<String,Object> updates);
    @GetMapping("/events/{eventId}/checkAvailability")
    boolean checkEventAvailability(@PathVariable Integer eventId, @RequestParam Integer numSeats);
}
