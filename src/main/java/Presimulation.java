import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;

public class Presimulation {

    //private static GameState game_state;

    public static void presimualationSetup(GameState game_state) throws IOException, JSONException {
        setSectors(game_state);
        setTramlines(game_state);
        spawnTrams(game_state);
        setRandomEvents(game_state);
    }

    /**
     * Set sectors with stops based on prepared map
     */
    private static void setSectors(GameState game_state) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tram_map.json");
        ResourceHandler.addSectorsAndStops(arr, game_state);
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private static void setTramlines(GameState game_state) throws IOException, JSONException{
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tramlines.json");
        ResourceHandler.addTramlines(arr, game_state);
    }

    /**
     * Spawn trams based on tramlines
     */
    private static void spawnTrams(GameState game_state){
        Tramline tramline;
        for (Tramline all_tramline : game_state.all_tramlines) {
            tramline = all_tramline;
            game_state.all_trams.add(new Tram(tramline, 0));
            game_state.all_trams.add(new Tram(tramline, 1));
        }
    }

    private static void setRandomEvents(GameState game_state) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/random_events.json");
        ResourceHandler.addRandomEvents(arr, game_state);

    }
}
