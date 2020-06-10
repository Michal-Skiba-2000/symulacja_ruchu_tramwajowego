import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ResourceHandler {
    private static List<RandomEvent> Events;

    public static JSONArray getJSONArrayFromFile(String filename) throws IOException, JSONException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONArray(content);
    }

    public static void addSectorsAndStops(JSONArray arr,GameState gameState) throws JSONException {
        JSONObject json;
        String stop_name;
        Stop stop = null;
        Sector sector;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            id = json.getInt("id");
            stop_name = json.getString("stop");

            if (!stop_name.equals("null")) {
                stop = new Stop(stop_name);
                gameState.all_stops.add(stop);
            }
            sector = new Sector(json.getInt("capacity"), stop, id);
            if (stop != null) {
                stop.addSector(sector);
            }
            stop = null;
            gameState.all_sectors.add(sector);

        }
    }

    public static void addTramlines(JSONArray arr,GameState gameState) throws JSONException {
        JSONObject json;
        JSONArray route_arr;
        List<Sector> tramline_sectors;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            route_arr = json.getJSONArray("route");
            tramline_sectors = getTramlineSectors(route_arr, gameState);
            id = json.getInt("id");
            gameState.all_tramlines.add(new Tramline(tramline_sectors, id));
        }
    }

    private static List<Sector> getTramlineSectors(JSONArray arr, GameState gameState) throws JSONException {
        List<Sector> sectors = new ArrayList<>();
        Sector sector;
        int index, j;
        for(int i = 0; i < arr.length(); i++){
            index = arr.getInt(i);

            sector = null;
            j = 0;
            do{
                if(index == gameState.all_sectors.get(j).getId()) sector = gameState.all_sectors.get(j);
                j++;
            }while(sector == null);
            if(!sectors.contains(sector)){
                sectors.add(sector);
            }
        }
        return sectors;
    }

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
            gameState.all_possible_events.add(new RandomEvent(duration, probability,name,id));
        }
    }
}