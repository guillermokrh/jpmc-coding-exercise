package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1, .50);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 8, 30), LocalTime.of(18, 0)));
        assertEquals(6.25, showing.calculateTicketPrice());
    }
    void regularMovieWithNoDiscount() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0, 0);
        Showing showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.of(2022, 8, 30), LocalTime.of(18, 0)));
        assertEquals(12.5, showing.calculateTicketPrice());
    }
}
