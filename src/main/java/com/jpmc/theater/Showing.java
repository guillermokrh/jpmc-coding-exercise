package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }


    // Add date/time logic here 
    public double getTimeDiscount(){
        double timeDiscount = 0;
        //Time Discount
        LocalDateProvider dateProvider = LocalDateProvider.singleton(); 
        LocalDateTime min = LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(11, 0));
        LocalDateTime max = LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(16, 0));

        if ((showStartTime.isAfter(min) || showStartTime.isEqual(min)) && showStartTime.isBefore(max)){
           timeDiscount = movie.getTicketPrice() * 0.25;
        }

       return timeDiscount;
    }

    public double getDateDiscount(){
        double dateDiscount = 0;
        //Day discount
        LocalDateProvider dateProvider = LocalDateProvider.singleton(); 
        LocalDate today = dateProvider.currentDate();
        LocalDate monthDay7 = LocalDate.of(dateProvider.currentDate().getYear(), dateProvider.currentDate().getMonthValue(), 7);
        if (today.isEqual(monthDay7)){
            dateDiscount = 1;
        }

        return dateDiscount;
    }

    public double getSequenceDiscount(){

        double sequenceDiscount = 0;
        if (sequenceOfTheDay == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (sequenceOfTheDay == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        return sequenceDiscount;
    }

    public double calculateDiscount() {
        List<Double> discounts = Arrays.asList(getTimeDiscount(), getDateDiscount(), getSequenceDiscount(), movie.getSpecialCodeDiscount());
        Double maximumDiscount = Collections.max(discounts);

        return maximumDiscount;
    }

    public double calculateTicketPrice() {
        double ticketPrice = getFaceValuePrice() - calculateDiscount();

        //Round ticket price as needed
        ticketPrice = Math.round(ticketPrice * 100.0);
        ticketPrice = ticketPrice/100.0;

        return ticketPrice;
    }

    public double getFaceValuePrice(){
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    private double calculateFee(int audienceCount) {
        return calculateTicketPrice() * audienceCount;
    }
}
