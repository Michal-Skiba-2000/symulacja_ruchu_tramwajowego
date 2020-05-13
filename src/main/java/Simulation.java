import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

/**
 * Main class that handles the simulation
 */
public class Simulation {

    private List<Tram> all_trams = new ArrayList<Tram>();
    private List<Sector> all_sectors = new ArrayList<Sector>();
    private List<Sector> sectors_to_repair = new ArrayList<>();
    private List<RandomEvent> all_possible_events = new ArrayList<RandomEvent>();
    public Tramlines stan= new Tramlines();
    private LocalTime time;

    /**
     * Set sectors based on prepared map
     */
    private void set_sectors(){


        System.out.println("set sectors");
    }
    /**
     * Set stops based on prepared map
     */
    private void set_stops(){

        System.out.println("set stops");
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
        for(int i = 0; i < all_trams.size(); i++){
            all_trams.get(i).make_move();
        }
    }

    /**
     * Repair all inactive sectors
     */
    private void repair_sectors(){
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
            resolve_random_events();
            spawn_passengers();
            move_trams();
            repair_sectors();
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
        set_stops();
        set_tramlines();
        spawn_trams();
        simulate();
    }

    public static void main(String[] args){
        Simulation simulation = new Simulation();
    }
}
