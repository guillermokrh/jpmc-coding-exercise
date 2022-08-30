package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFeewithTimeDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1, .2),
                1,
                LocalDateTime.of(2022, 8, 30, 15, 0)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.14);
    }

    @Test
    void totalFeewithDateDiscount() {
        var customer = new Customer("Clark Kent", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0, 0),
                4,
                LocalDateTime.of(2022, 8, 7, 18, 0)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 34.5);
    }
}
