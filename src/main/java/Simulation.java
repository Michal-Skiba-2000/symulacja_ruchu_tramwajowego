import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
  Main class that handles the simulation
*/
public class Simulation {

    private List<Tram> all_trams = new ArrayList<Tram>();
    private List<Sector> all_sectors = new ArrayList<Sector>();
    private List<Sector> sectors_to_repair = new ArrayList<>();
    private List<RandomEvent> all_possible_events = new ArrayList<RandomEvent>();
    private List<Tramline> all_tramlines = new ArrayList<>();
    public Tramlines stan= new Tramlines();
    private LocalTime time;

    /**
     * Set sectors with stops based on prepared map
     */
    private void set_sectors() throws IOException, JSONException{
        String filename = "src/main/resources/tram_map.json";
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray arr = new JSONArray(content);
        JSONArray arr2;
        JSONObject json;
        Stop stop;
        Sector sector;
        int capacity;
        String stop_name;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            capacity = json.getInt("capacity");
            stop_name = json.getString("stop");
            stop = new Stop(stop_name);
            sector = new Sector(capacity, stop);
            stop.addSector(sector);
            all_sectors.add(sector);
        }
        int sec_index;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            arr2 = json.getJSONArray("connected");
            sector = all_sectors.get(i);
            for(int j = 0; j < arr2.length(); j++){
                sec_index = arr2.getInt(j) - 1;
                sector.connected_sectors.add( all_sectors.get(sec_index) );
            }
        }
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private void set_tramlines() throws IOException, JSONException{
        String filename = "src/main/resources/tramlines.json";
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray tramlines_arr = new JSONArray(content);
        JSONArray route_arr;
        JSONObject tramline_obj;
        Tramline tramline;
        List<Sector> tramline_sectors;
        int start, stop;
        for(int i = 0; i < tramlines_arr.length(); i++){
            tramline_obj = tramlines_arr.getJSONObject(i);
            route_arr = tramline_obj.getJSONArray("route");
            tramline_sectors = new ArrayList<>();
            for(int j = 0; j < route_arr.length(); j++){
                start = route_arr.getJSONArray(j).getInt(0);
                stop = route_arr.getJSONArray(j).getInt(1);
                for(int k = start; k <= stop; k++){ tramline_sectors.add(all_sectors.get(k-1)); }
            }
            tramline = new Tramline(tramline_sectors);
            all_tramlines.add(tramline);
        }
    }

    /**
     * Spawn trams based on tramlines
     */
    private void spawn_trams(){
        Tramline tramline;
        for(int i = 0; i < all_tramlines.size(); i++){
            tramline = all_tramlines.get(i);
            all_trams.add(new Tram(tramline, 0));
            all_trams.add(new Tram(tramline, 1));
        }
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
    private Simulation()  throws IOException, JSONException{
        time = LocalTime.of(5, 0, 0);
        set_sectors();
        set_tramlines();
        spawn_trams();
        simulate();
    }

    public static void main(String[] args) throws IOException, JSONException {
        Simulation simulation = new Simulation();
    }
}
