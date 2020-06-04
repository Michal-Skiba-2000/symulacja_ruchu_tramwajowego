import org.json.JSONException;

import java.io.IOException;
import java.time.LocalTime;

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
        double probability=Math.random()*100;
        int flag=0;
        for(int j = 0; j< gameState.all_possible_events.size()&&flag==0; j++) {
            if(probability< gameState.all_possible_events.get(j).probability) {
                int sector_id=(int) (Math.round(Math.random()*1000)% gameState.all_sectors.size());
                gameState.all_sectors.get(sector_id).assignEvent(gameState.all_possible_events.get(j));
                gameState.sectors_to_repair.add(gameState.all_sectors.get(sector_id));
                statistics.totalNumberOfEvents.add(gameState.all_possible_events.get(j));

                flag=1;
            }
        }

    }

    /**
     * Spawn passengers on trams based on time
     */
    private void spawnPassengers() {
        int maxNumberOfPassengers = 0;
        if(time.getHour()<6)maxNumberOfPassengers=2;
        if(time.getHour()<9 && time.getHour()>=6)maxNumberOfPassengers=5;
        if(time.getHour()>=9&&time.getHour()<14)maxNumberOfPassengers=2;
        if(time.getHour()>=14&&time.getHour()<18)maxNumberOfPassengers=5;
        if(time.getHour()>=18&&time.getHour()<21)maxNumberOfPassengers=2;
        if(time.getHour()>=21&&time.getMinute()<30)maxNumberOfPassengers=1;
        if(time.getHour()>=21&&time.getMinute()>30)maxNumberOfPassengers=1;

        if(maxNumberOfPassengers!=0)
        {
            int numberOfPassengers= (int) (Math.round(Math.random() * 1000)) % maxNumberOfPassengers;

            for (int i =0;i<numberOfPassengers;i++) {
                int start = (int) (Math.round(Math.random() * 100)) % gameState.all_stops.size();
                Passenger passenger = new Passenger(gameState.all_stops.get(start),gameState);
                passenger.spawnTime=LocalTime.of(time.getHour(),time.getMinute());
                gameState.all_passengers.add(passenger);
                gameState.all_stops.get(start).passengers_on.add(passenger);
                statistics.totalNumberOfPassengers.add(passenger);
            }

        }

    }

    /**
     * Resolve move function on all trams
     */
    private void moveTrams(){
        for (Tram all_tram : gameState.all_trams) {
            all_tram.make_move(time, gameState);
        }
    }

    /**
     * Repair all inactive sectors
     */
    private void repairSectors(){
        if(gameState.sectors_to_repair != null){
            Sector sector;
            for(int i = gameState.sectors_to_repair.size()-1; i >= 0; i--){
                sector = gameState.sectors_to_repair.get(i);
                sector.repair();
                if(sector.is_active){ gameState.sectors_to_repair.remove(i); }
            }
        }
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
        for(int i=0;i<gameState.all_passengers.size();i++){
            gameState.all_passengers.get(i).loadTime=time;
        }
        statistics.totalNumberOfUnhandled.addAll(gameState.all_passengers);
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
        statistics.showStatistics();
    }

    public static void main(String[] args) throws IOException, JSONException {
        Simulation simulation = new Simulation();
    }
}
