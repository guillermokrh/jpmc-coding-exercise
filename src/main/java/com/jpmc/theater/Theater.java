package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        //Spiderman, Price: 12.50, Special Movie
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1, .2);
        //Turning Red, Price: 11
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0, .2);
        //The Batman, Price: 9
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0, .2);
        this.schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public List<Showing> getSchedule(){
        return this.schedule;
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getFaceValuePrice())
        );
        System.out.println("===================================================");
    }

    public void printJSON() {
        System.out.println("{");
        System.out.println("  \"theater\": [");
        System.out.println("     \"date\": " + "\"" + provider.currentDate() + "\",");
        System.out.println("     \"showings\": [");
        for (int i = 0; i < schedule.size(); i++){
            System.out.println("      {");
            System.out.println("         \"sequence\": " + "\"" +schedule.get(i).getSequenceOfTheDay() + "\"");
            System.out.println("         \"startTime\": " + "\"" + schedule.get(i).getStartTime() + "\"");
            System.out.println("         \"movieTitle\": " + "\"" +schedule.get(i).getMovie().getTitle() + "\"");
            System.out.println("         \"runningTime\": " + "\"" + humanReadableFormat(schedule.get(i).getMovie().getRunningTime()) + "\"");
            System.out.println("         \"price\": \"$" + schedule.get(i).getFaceValuePrice() + "\"");
            if (i < schedule.size()-1){
                System.out.println("      },");
            } else {
                System.out.println("      }");
            }
        }
        System.out.println("    ]");
        System.out.println("  ]");
        System.out.println("}");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
       
        //Add new customer, Guillermo 
        Customer customer = new Customer("Guillermo", "1");
        System.out.println(customer.toString()); 

        //Add new customer, Kimberly
        Customer customerKim = new Customer("Kimberly", "2");
        System.out.println(customerKim.toString()); 

        //Add new customer, Antonio 
        Customer customerAntonio = new Customer("Antonio", "3");
        System.out.println(customerAntonio.toString()); 

        //Get theater schedule to get showings 
        List<Showing> schedule = theater.getSchedule();
        //System.out.println(schedule);

        //Get Turning Red Showing
        Showing turningRed1 = schedule.get(0);
        //Get Spider Man Showing
        Showing spiderman2 = schedule.get(1);
        //Get Batman Showing
        Showing batman = schedule.get(2);

        //Guillermo Reservation
        //Should have $3 discount
        Reservation guillermoReservation = new Reservation(customer, turningRed1, 1);
        guillermoReservation.printReservation();

        //Kimberly Reservation
        //Should have 20% discount and $2 discount, which is 20% discount
        Reservation kimberlyReservation = new Reservation(customerKim, spiderman2, 1);
        kimberlyReservation.printReservation();

        //Antonio Reservation
        //Should have no discount 
        Reservation antonioReservation = new Reservation(customerAntonio, batman, 3);
        antonioReservation.printReservation();

        theater.printJSON();
    }
}
