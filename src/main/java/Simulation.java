import java.util.List;
import java.time.LocalTime;

public class Simulation {
    // private List<Tram> all_trams = new ArrayList<Tram>();
    // private List<Sector> all_sectors = new ArrayList<Sector>();
    // private List<RandomEvent> all_possible_events = new ArrayList<RandomEvent>();
    private LocalTime time;

    public void spawn_passengers(){
        System.out.println("spawn");
    }

    public void resolve_random_events(){
        System.out.println("resolve");
    }

    public void move_trams(){
        System.out.println("move");
    }

    public Simulation(){
        // Set simualtion parameters
    }

    public static void main(String[] args){
        // Start simulation
    }
}
