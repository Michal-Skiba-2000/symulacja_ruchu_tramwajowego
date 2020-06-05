import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Statistics {
    LocalTime longestWaiting=null;
    ArrayList<Passenger> totalNumberOfPassengers=new ArrayList<>();
    ArrayList<RandomEvent> totalNumberOfEvents=new ArrayList<>();
    ArrayList<Passenger> totalNumberOfUnhandled=new ArrayList<>();

    public void showStatistics(GameState gameState){
        System.out.println("Total numer of passengers today: "+ totalNumberOfPassengers.size()+".");
        System.out.println("Total number of unhandled passengers today: "+totalNumberOfUnhandled.size()+".");
        longestWaitingPassenger();
        eventsOccured(gameState);
    }

    private void longestWaitingPassenger() {
        longestWaiting = totalNumberOfPassengers.get(0).loadTime;
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
        longestWaiting.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
        for (int i = 0; i < totalNumberOfPassengers.size(); i++) {
            LocalTime currentPassenger = totalNumberOfPassengers.get(i).loadTime;
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getHour(), ChronoUnit.HOURS);
            currentPassenger.minus(totalNumberOfPassengers.get(0).loadTime.getMinute(), ChronoUnit.MINUTES);
            if (longestWaiting.compareTo(currentPassenger) < 0) {
                longestWaiting = currentPassenger;
                longestWaiting = longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
                longestWaiting = longestWaiting.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
                currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getHour(), ChronoUnit.HOURS);
                currentPassenger = currentPassenger.minus(totalNumberOfPassengers.get(0).spawnTime.getMinute(), ChronoUnit.MINUTES);
                if (longestWaiting.compareTo(currentPassenger) < 0) {
                    longestWaiting = currentPassenger;
                }
            }
            }
        System.out.println("The unluckiest passenger waited " + longestWaiting.toString()+".");
        }


            private void eventsOccured(GameState gameState){
                System.out.println("Total number of events: " + totalNumberOfEvents.size()+".");
                int[] eventsId = new int[5];
                for (RandomEvent numberOfEvent : totalNumberOfEvents) {
                    eventsId[numberOfEvent.id - 1]++;
                }

                for (int i = 0; i<5; i++){
                    if(eventsId[i]!=0)
                        System.out.println("Event "+gameState.all_possible_events.get(i).name +" occured "+ eventsId[i]+" times.");
                }

                int howLong = 0;
                for (RandomEvent totalNumberOfEvent : totalNumberOfEvents) {
                    howLong += totalNumberOfEvent.duration;
                }
                System.out.println("Total delay cause by events: " + howLong + " minutes. ");

            }
        }

