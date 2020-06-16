import org.json.JSONException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
  Main class that handles the simulation
*/
public class  Simulation{

    private final GameState gameState = new GameState();
    private final Statistics statistics=new Statistics();

    LocalTime time;
    /**
     * Resolve random events from all_possible_events list
     * and if any happen set event to tram or sector
     */
    private void resolveRandomEvents(){
        double probability=Math.random()*10000;
        int flag=0;
        for(int j = 0; j< gameState.allPossibleEvents.size()&&flag==0; j++) {
            if(probability< gameState.allPossibleEvents.get(j).probability) {
                int sector_id=(int) (Math.round(Math.random()*1000)% gameState.allSectors.size());
                gameState.allSectors.get(sector_id).assignEvent(gameState.allPossibleEvents.get(j));
                gameState.sectorsToRepair.add(gameState.allSectors.get(sector_id));
                statistics.totalNumberOfEvents.add(gameState.allPossibleEvents.get(j));

                flag=1;
            }
        }

    }

    /**
     * Spawn passengers on trams based on time
     */
    private void spawnPassengers() {
        double x=time.getHour()+(1.0*time.getMinute()/60);
        int maxNumberOfPassengers;
        if(x<18) maxNumberOfPassengers = (int) Math.round(-5*(x-6)*(x-9)*(x-14)*(x-18)/50+8);
        else maxNumberOfPassengers= (int) (5.0*11/(x-16)-1.5);

        if(maxNumberOfPassengers<0)maxNumberOfPassengers=0;

        if(maxNumberOfPassengers!=0)
        {
            int numberOfPassengers= (int) (Math.round(Math.random() * 1000)) % maxNumberOfPassengers;

            for (int i =0;i<numberOfPassengers;i++) {
                int start = (int) (Math.round(Math.random() * 10000)) % gameState.allStops.size();
                Passenger passenger = new Passenger(gameState.allStops.get(start),gameState);
                passenger.spawnTime=LocalTime.of(time.getHour(),time.getMinute());
                gameState.allPassengers.add(passenger);
                gameState.allStops.get(start).passengersOn.add(passenger);
                statistics.totalNumberOfPassengers.add(passenger);
            }
        }
    }

    /**
     * Resolve move function on all trams
     */
    private void moveTrams(){
        for (Tram allTram : gameState.allTrams) {
            allTram.makeMove(time, gameState);
        }
    }

    /**
     * Repair all inactive sectors
     */
    private void repairSectors(){
        List<Sector> sectorsToRemove = new ArrayList<>();
        for(Sector sector: gameState.sectorsToRepair){
            sector.repair();
            if(sector.isActive){ sectorsToRemove.add(sector); }
        }
        gameState.sectorsToRepair.removeAll(sectorsToRemove);
    }

    /**
     * Repeat actions for one turn,
     * random events, spawning passengers, moving trams
     * until 23:00
     */
    private void simulate(){
        while( !(time.getHour() == 23 && time.getMinute() == 0) ){
            resolveRandomEvents();
            spawnPassengers();
            moveTrams();
            repairSectors();
            time = time.plusMinutes(1);
        }
        for(Passenger passenger: gameState.allPassengers){
            passenger.loadTime=time;
        }
        statistics.totalNumberOfUnhandled.addAll(gameState.allPassengers);
    }

    /**
     * Set default parameters for simulation
     * Start simulating
     * Show statistics in the end
     */
    private Simulation()  throws IOException, JSONException{
        time = LocalTime.of(5, 0);
        Presimulation.presimualationSetup(gameState);
        simulate();
        statistics.showStatistics(gameState);
    }

    public static void main(String[] args) throws IOException, JSONException {
        Simulation simulation = new Simulation();
    }
}
