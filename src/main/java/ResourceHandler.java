import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.events.Event;
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

    public static void addSectorsAndStops(JSONArray arr, List<Sector> sectors_list, List<Stop> stops_list) throws JSONException {
        JSONObject json;
        String stop_name;
        Stop stop = null;
        Sector sector;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            id = json.getInt("id");
            stop_name = json.getString("stop");
            if(!stop_name.equals("null")) {
                stop = new Stop(stop_name);
                stops_list.add(stop);
            }
            sector = new Sector(json.getInt("capacity"), stop, id);
            stop.addSector(sector);
            sectors_list.add(sector);
        }
    }

    public static void addTramlines(JSONArray arr, List<Tramline> tramlines_list, List<Sector> sectors_list) throws JSONException {
        JSONObject json;
        JSONArray route_arr;
        List<Sector> tramline_sectors;
        int id;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);
            route_arr = json.getJSONArray("route");
            tramline_sectors = getTramlineSectors(route_arr, sectors_list);
            id = json.getInt("id");
            tramlines_list.add(new Tramline(tramline_sectors, id));
        }
    }

    private static List<Sector> getTramlineSectors(JSONArray arr, List<Sector> sectors_list) throws JSONException {
        List<Sector> sectors = new ArrayList<>();
        Sector sector;
        int index, j;
        for(int i = 0; i < arr.length(); i++){
            index = arr.getInt(i);
            sector = null;
            j = 0;
            do{
                if(index == sectors_list.get(j).getId()) sector = sectors_list.get(j);
                j++;
            }while(sector == null);
            sectors.add(sector);
        }
        return sectors;
    }

    public static void addRandomEvents(JSONArray arr,List<RandomEvent> all_possible_events) throws JSONException {
        JSONObject json;
        int duration;
        double probability;
        for(int i = 0; i < arr.length(); i++){
            json = arr.getJSONObject(i);

            duration = json.getInt("duration");
            probability = json.getDouble("probability");
            all_possible_events.add(new RandomEvent(duration, probability));
        }
    }
}