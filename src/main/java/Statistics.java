import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Statistics {
    LocalTime longestWaiting=null;
    ArrayList<Passenger> totalNumberOfPassengers=new ArrayList<>();
    ArrayList<RandomEvent> totalNumberOfEvents=new ArrayList<>();
    ArrayList<Passenger> totalNumberOfUnhandled=new ArrayList<>();

    public void showStatistics(){
        System.out.println("Total numer of passengers today: "+ totalNumberOfPassengers.size());
        System.out.println("Total number of unhandled passengers today: "+totalNumberOfUnhandled.size());
        longestWaitingPassenger();
        eventsOccured();
    }

    private void longestWaitingPassenger(){
        longestWaiting=totalNumberOfPassengers.get(0).loadTime;
<<<<<<< HEAD
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
        for (int i = 0; i< totalNumberOfPassengers.size(); i++){
            LocalTime currentPassenger=totalNumberOfPassengers.get(i).loadTime;
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
            if(longestWaiting.compareTo(currentPassenger)<0){
                longestWaiting=currentPassenger;
=======
        longestWaiting=longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
        longestWaiting=longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
        for (Passenger totalNumberOfPassenger : totalNumberOfPassengers) {
            LocalTime currentPassenger = totalNumberOfPassenger.loadTime;
            currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
            currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
            if (longestWaiting.compareTo(currentPassenger) < 0) {
                longestWaiting = currentPassenger;
                System.out.println(longestWaiting.toString());
>>>>>>> 68673447b6a5d0e17c42a18adcee17a23df376ba
            }
        }
        System.out.println("The unluckiest passenger waited " + longestWaiting.toString());
    }

    private void eventsOccured(){
        System.out.println("Total number of events: "+totalNumberOfEvents.size());
        int howLong=0;
        for (RandomEvent totalNumberOfEvent : totalNumberOfEvents) {
            howLong += totalNumberOfEvent.duration;
        }
        System.out.println("Total duration of events: "+howLong+ " minutes ");
    }
}
