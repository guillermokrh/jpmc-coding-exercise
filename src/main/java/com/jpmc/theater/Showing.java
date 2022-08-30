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


    //Get 25% discount if movie shown between 11:00AM and 4PM  
    public double getTimeDiscount(){
        double timeDiscount = 0;
        LocalTime min = LocalTime.of(10, 59);
        LocalTime max = LocalTime.of(16, 0);
        LocalTime showTime = showStartTime.toLocalTime();

        if (showTime.isAfter(min) && showTime.isBefore(max)){
           timeDiscount = movie.getTicketPrice() * 0.25;
        }

       return timeDiscount;
    }

    //Get $1 discount if movie is shown on 7th of the month
    public double getDateDiscount(){
        double dateDiscount = 0;
        int showDay = showStartTime.toLocalDate().getDayOfMonth(); 
        if (showDay == 7){
            dateDiscount = 1;
        }

        return dateDiscount;
    }

    //Get $3 discount if movie is first movie shown of the day
    //Get $2 discount if movie is the second movie shown of the day 
    public double getSequenceDiscount(){

        double sequenceDiscount = 0;
        if (sequenceOfTheDay == 1) {
            sequenceDiscount = 3; 
        } else if (sequenceOfTheDay == 2) {
            sequenceDiscount = 2; 
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
        ticketPrice = (double) Math.round(ticketPrice * 100.0)/100;

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
