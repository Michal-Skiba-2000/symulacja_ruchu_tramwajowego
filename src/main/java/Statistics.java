import java.time.Duration;
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
        System.out.println("Total number of events: "+totalNumberOfEvents.size());
        longestWaitingPassenger();
    }

    private void longestWaitingPassenger(){
        longestWaiting=totalNumberOfPassengers.get(0).loadTime;
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
        for (int i = 0; i< totalNumberOfPassengers.size(); i++){
            LocalTime currentPassenger=totalNumberOfPassengers.get(i).loadTime;
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
            if(longestWaiting.compareTo(currentPassenger)<0){
                longestWaiting=currentPassenger;
            }
        }
        System.out.println("The unluckiest passenger waited " + longestWaiting.toString());
    }

}
