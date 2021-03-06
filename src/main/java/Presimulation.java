import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;

public class Presimulation {

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
        Sector sector;
        int size;
        for (Tramline tramline : gameState.allTramlines) {
            size = tramline.sectors.size();
            sector = tramline.sectors.get(size-1);
            gameState.allTrams.add(new Tram(tramline, 0, sector));
            sector = tramline.sectors.get(0);
            gameState.allTrams.add(new Tram(tramline, 1, sector));
            sector = tramline.sectors.get((int) size/2);
            gameState.allTrams.add(new Tram(tramline, 0, sector));
            gameState.allTrams.add(new Tram(tramline, 1, sector));
        }
    }

    /**
     * Set events based on prepared data
     */
    private static void setRandomEvents(GameState gameState) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/random_events.json");
        ResourceHandler.addRandomEvents(arr, gameState);

    }
}
