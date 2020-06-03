import java.time.Duration;
import java.util.ArrayList;

public class Statistics {
    Duration longestWaiting=null;
    ArrayList<Passenger> totalNumberOfPassengers;
    ArrayList<RandomEvent> totalNumberOfEvents;
    ArrayList<Passenger> totalNumberOfUnhandled;

    public void showStatistics(){
        System.out.println("Total numer of passengers today: "+ totalNumberOfPassengers.size());
        System.out.println("Total number of unhandled passengers today: "+totalNumberOfUnhandled.size());
        System.out.println("Total number of events: "+totalNumberOfEvents.size());
        longestWaitingPassenger();
        System.out.println("The unluckiest passenger waited " + longestWaiting.toHours()+" hours "+longestWaiting.toMinutes()+" minutes,and "+longestWaiting.toSeconds()+" seconds");
    }

    private void longestWaitingPassenger(){
        longestWaiting=Duration.between(totalNumberOfPassengers.get(0).loadTime, totalNumberOfPassengers.get(0).spawnTime);
        for (int i = 0; i< totalNumberOfPassengers.size(); i++){
            Duration currentPassenger= Duration.between(totalNumberOfPassengers.get(i).loadTime, totalNumberOfPassengers.get(i).spawnTime);
            if(longestWaiting.toSeconds()<currentPassenger.toSeconds()){
                longestWaiting=currentPassenger;
            }
        }
    }

}
