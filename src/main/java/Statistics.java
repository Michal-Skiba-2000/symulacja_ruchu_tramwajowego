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
        longestWaiting=longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
        longestWaiting=longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
        for (Passenger totalNumberOfPassenger : totalNumberOfPassengers) {
            LocalTime currentPassenger = totalNumberOfPassenger.loadTime;
            currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
            currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
            if (longestWaiting.compareTo(currentPassenger) < 0) {
                longestWaiting = currentPassenger;
                System.out.println(longestWaiting.toString());
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
