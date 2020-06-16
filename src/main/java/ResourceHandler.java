import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ResourceHandler {

    /**
     * Returns bytes loaded from file
     * @param filename pathway to file
     * @return content of file
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray getJSONArrayFromFile(String filename) throws IOException, JSONException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONArray(content);
    }

    /**
     * Returns sectors data retrieve from JSONArray
     * @param arr JSONArray with loaded file
     * @param gameState current game state
     * @throws JSONException
     */
    public static void addSectorsAndStops(JSONArray arr,GameState gameState) throws JSONException {
        JSONObject json;
        String stopName;
        Stop stop = null;
        Sector sector;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            id = json.getInt("id");
            stopName = json.getString("stop");

            if (!stopName.equals("null")) {
                stop = new Stop(stopName);
                gameState.allStops.add(stop);
            }
            sector = new Sector(json.getInt("capacity"), stop, id);
            if (stop != null) {
                stop.addSector(sector);
            }
            stop = null;
            gameState.allSectors.add(sector);

        }
    }

    /**
     * Retrieve tramlines data from JSONArray
     * @param arr JSONArray with loaded file
     * @param gameState current game state
     * @throws JSONException
     */
    public static void addTramlines(JSONArray arr,GameState gameState) throws JSONException {
        JSONObject json;
        JSONArray routeArr;
        List<Sector> tramlineSectors;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            routeArr = json.getJSONArray("route");
            tramlineSectors = getTramlineSectors(routeArr, gameState);
            id = json.getInt("id");
            gameState.allTramlines.add(new Tramline(tramlineSectors));
        }
    }

    /**
     * Returns list of sectors
     * in this sectors tram moves
     * @param arr JSONArray with loaded file
     * @param gameState current game state
     * @return sectors in which tram moves
     * @throws JSONException
     */
    private static List<Sector> getTramlineSectors(JSONArray arr, GameState gameState) throws JSONException {
        List<Sector> sectors = new ArrayList<>();
        Sector sector;
        int index, j;
        for(int i = 0; i < arr.length(); i++){
            index = arr.getInt(i);
            sector = null;
            j = 0;
            do{
                if(index == gameState.allSectors.get(j).getId()) sector = gameState.allSectors.get(j);
                j++;
            }while(sector == null);
            if(!sectors.contains(sector)){
                sectors.add(sector);
            }
        }
        return sectors;
    }

    /**
     * Retrieve events data from JSONArray
     * @param arr JSONArray with loaded file
     * @param gameState current game state
     * @throws JSONException
     */
    public static void addRandomEvents(JSONArray arr,GameState gameState) throws JSONException {
        JSONObject json;
        int duration;
        String name;
        int id;
        int probability;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            name=json.getString("name");
            id=json.getInt("id");
            duration = json.getInt("duration");
            probability = json.getInt("probability");
            gameState.allPossibleEvents.add(new RandomEvent(duration, probability,name,id));
        }
    }
}