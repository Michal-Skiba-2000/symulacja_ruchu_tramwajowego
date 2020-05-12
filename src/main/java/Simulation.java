import java.util.List;
import java.time.LocalTime;

/**
 * Main class that handles the simulation
 */
public class Simulation {
     // Musimy wpierw zaimplementować klasy Tram, Sector, RandomEvent
     // zanim zaczniemy implementację przeprowadzenia symulacji

    // private List<Tram> all_trams = new ArrayList<Tram>();
    // private List<Sector> all_sectors = new ArrayList<Sector>();
    // private List<RandomEvent> all_possible_events = new ArrayList<RandomEvent>();
    private LocalTime time;

    /**
     * Set sectors based on prepared map
     */
    private void set_sectors(){
        System.out.println("set sectors");
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private void set_tramlines(){
        System.out.println("set tramlines");
    }

    /**
     * Spawn trams based on tramlines
     */
    private void spawn_trams(){
        System.out.println("spawn trams");
    }

    /**
     * Resolve random events from all_possible_events list
     * and if any happen set event to tram or sector
     */
    private void resolve_random_events(){
        System.out.println("resolve random events");
    }

    /**
     * Spawn passengers on trams based on time
     */
    private void spawn_passengers() {
        System.out.println("spawn passengers");
    }

    /**
     * Resolve move function on all trams
     */
    private void move_trams(){
        System.out.println("move trams");
    }

    /**
     * Repeat actions for one turn,
     * random events, spawning passengers, moving trams
     * until 23:00
     */
    private void simulate(){
        // potrzebujemy jakichś struktur w których będziemy zapisywać dane symulacji
        while( !(time.getHour() == 23 && time.getMinute() == 0) ){
            resolve_random_events();
            spawn_passengers();
            move_trams();
            time = time.plusMinutes(1);
        }
    }

    /**
     * Set default parameters for simulation
     * Start simulating
     */
    private Simulation(){
        time = LocalTime.of(5, 0, 0);
        set_sectors();
        set_tramlines();
        spawn_trams();
        simulate();
    }

    public static void main(String[] args){
        Simulation simulation = new Simulation();
    }
}
