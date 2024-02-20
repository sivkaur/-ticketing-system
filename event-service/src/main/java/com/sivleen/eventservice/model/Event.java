package com.sivleen.eventservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate date;
    private String location;
    private Integer totalSeats;
    private Integer seatsBooked;

    public Event(Integer id, String name, LocalDate date, String location, Integer totalSeats, Integer seatsBooked) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.totalSeats = totalSeats;
        this.seatsBooked = seatsBooked;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", totalSeats=" + totalSeats +
                ", seatsBooked=" + seatsBooked +
                '}';
    }

    public Event() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public Integer getSeatsBooked() {
        return seatsBooked;
    }
}
