package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private Movie movie;
    private int audienceCount;
    private double pricePerTicket;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.movie = showing.getMovie();
        this.pricePerTicket = showing.getDiscountFee();
    }

    public double totalFee() {
        return pricePerTicket * audienceCount;
    }

    public double pricePerTicket() {
        return pricePerTicket;
    }

    public void printReservation(){
        System.out.println("===================================================");
        System.out.println("Customer: " + customer.toString());
        System.out.println("Showing: " + showing.getSequenceOfTheDay());
        System.out.println("Audience Count: " + audienceCount);
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Total Fee: " + this.totalFee());
        System.out.println("Price Per Ticket: " + this.pricePerTicket());
        System.out.println("===================================================");
    }
}