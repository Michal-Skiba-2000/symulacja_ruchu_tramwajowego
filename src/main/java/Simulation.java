import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import java.io.IOException;

/**
  Main class that handles the simulation
*/
public class  Simulation {

    private List<Tram> all_trams = new ArrayList<Tram>();
    private List<Sector> all_sectors = new ArrayList<Sector>();
    private List<Sector> sectors_to_repair = new ArrayList<>();
    private List<RandomEvent> all_possible_events = new ArrayList<RandomEvent>();
    private List<Tramline> all_tramlines = new ArrayList<>();
    private List<Passenger> all_passengers = new ArrayList<>();
    private List<Stop> all_stops = new ArrayList<>();
    private LocalTime time;
    private RandomEvent event;

    /**
     * Resolve random events from all_possible_events list
     * and if any happen set event to tram or sector
     */
    private void resolveRandomEvents(){
        int i= (int) Math.round(Math.random()*1000)%100;
        if(i< event.probability) {
            i=(int) (Math.round(Math.random()*1000)%all_sectors.size());
            all_sectors.get(i).assignEvent(event);
            sectors_to_repair.add(all_sectors.get(i));
        }
    }

    /**
     * Spawn passengers on trams based on time
     */
    private void spawnPassengers() {
        int start= (int) (Math.round(Math.random()*1000)% all_stops.size());
        Passenger passenger = new Passenger(all_stops.get(start), all_stops);
        all_passengers.add(passenger);
    }

    /**
     * Resolve move function on all trams
     */
    private void moveTrams(){
        for (Tram all_tram : all_trams) {
            all_tram.make_move();
        }
    }

    /**
     * Repair all inactive sectors
     */
    private void repairSectors(){
        if(sectors_to_repair != null){
            Sector sector;
            for(int i = sectors_to_repair.size()-1; i >= 0; i--){
                sector = sectors_to_repair.get(i);
                sector.repair();
                if(sector.is_active){ sectors_to_repair.remove(i); }
            }
        }
    }

    /**
     * Repeat actions for one turn,
     * random events, spawning passengers, moving trams
     * until 23:00
     */
    private void simulate(){
        // potrzebujemy jakichś struktur w których będziemy zapisywać dane symulacji
        while( !(time.getHour() == 23 && time.getMinute() == 0) ){
            resolveRandomEvents();
            spawnPassengers();
            moveTrams();
            repairSectors();
            time = time.plusMinutes(1);
        }
    }

    /**
     * Set default parameters for simulation
     * Start simulating
     */
    private Simulation()  throws IOException, JSONException{
        time = LocalTime.of(5, 0, 0);
        Presimulation.presimualationSetup(all_sectors, all_stops, all_trams, all_tramlines, all_possible_events);
        simulate();
    }

    public static void main(String[] args) throws IOException, JSONException {
        Simulation simulation = new Simulation();
    }
}
