import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.util.List;

public class Presimulation {

    private static GameState game_state;

    public static void presimualationSetup(GameState gs) throws IOException, JSONException {
        game_state = gs;
        setSectors();
        setTramlines();
        spawnTrams();
        setRandomEvents();
    }

    /**
     * Set sectors with stops based on prepared map
     */
    private static void setSectors() throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tram_map.json");
        ResourceHandler.addSectorsAndStops(arr, game_state.all_sectors, game_state.all_stops);
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private static void setTramlines() throws IOException, JSONException{
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tramlines.json");
        ResourceHandler.addTramlines(arr, game_state.all_tramlines, game_state.all_sectors);
    }

    /**
     * Spawn trams based on tramlines
     */
    private static void spawnTrams(){
        Tramline tramline;
        for (Tramline all_tramline : game_state.all_tramlines) {
            tramline = all_tramline;
            game_state.all_trams.add(new Tram(tramline, 0));
            game_state.all_trams.add(new Tram(tramline, 1));
        }
    }

    private static void setRandomEvents() throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/random_events.json");
        ResourceHandler.addRandomEvents(arr, game_state.all_possible_events);

    }
}
