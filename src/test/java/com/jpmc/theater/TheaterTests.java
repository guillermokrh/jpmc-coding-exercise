package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TheaterTests {
    @Test
    void totalFeeForCustomerNoDiscount() {
        LocalDate date = LocalDate.of(2022, 8, 30);
        Theater theater = new Theater(date);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 6, 4);
        assertEquals(reservation.totalFee(), 36.00);
    }

    @Test
    void totalFeeForCustomerSequenceDiscount() {
        LocalDate date = LocalDate.of(2022, 8, 30);
        Theater theater = new Theater(date);
        Customer guillermo = new Customer("Guillermo", "id-12346");
        Reservation reservation = theater.reserve(guillermo, 1, 1);
        assertEquals(reservation.totalFee(), 8.00);
    }

    @Test
    void printMovieSchedule() {
        LocalDateProvider dateProvider = LocalDateProvider.singleton();
        LocalDate today = dateProvider.currentDate();
        Theater theater = new Theater(today);
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleJSON() {
        LocalDateProvider dateProvider = LocalDateProvider.singleton();
        LocalDate today = dateProvider.currentDate();
        Theater theater = new Theater(today);
        theater.printJSON();
    }
}
