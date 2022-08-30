package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
    private double specialCodeDiscountPercent;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode, double discountPercent) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
        this.specialCodeDiscountPercent = discountPercent;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    //Calculate Special Code Discount
    public double getSpecialCodeDiscount() {
        double specialDiscount = 0;
        if (specialCode == MOVIE_CODE_SPECIAL) {
            specialDiscount = ticketPrice * specialCodeDiscountPercent;  
        }

        return specialDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}