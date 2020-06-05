import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;

public class Presimulation {

    //private static GameState game_state;

    public static void presimualationSetup(GameState gameState) throws IOException, JSONException {
        setSectors(gameState);
        setTramlines(gameState);
        spawnTrams(gameState);
        setRandomEvents(gameState);
    }

    /**
     * Set sectors with stops based on prepared map
     */
    private static void setSectors(GameState gameState) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tram_map.json");
        ResourceHandler.addSectorsAndStops(arr, gameState);
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private static void setTramlines(GameState gameState) throws IOException, JSONException{
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tramlines.json");
        ResourceHandler.addTramlines(arr, gameState);
    }

    /**
     * Spawn trams based on tramlines
     */
    private static void spawnTrams(GameState gameState){
        Tramline tramline;
        for (Tramline all_tramline : gameState.all_tramlines) {
            tramline = all_tramline;
            gameState.all_trams.add(new Tram(tramline, 0));
            gameState.all_trams.add(new Tram(tramline, 1));
        }
    }

    private static void setRandomEvents(GameState gameState) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/random_events.json");
        ResourceHandler.addRandomEvents(arr, gameState);

    }
}
