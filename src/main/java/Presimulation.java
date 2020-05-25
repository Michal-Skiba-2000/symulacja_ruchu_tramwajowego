import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.util.List;

public class Presimulation {
    public static void presimualationSetup(List<Sector> all_sectors, List<Stop> all_stops, List<Tram> all_trams, List<Tramline> all_tramlines, List<RandomEvent> all_possible_events) throws IOException, JSONException {
        setSectors(all_sectors, all_stops);
        setTramlines(all_sectors, all_tramlines);
        spawnTrams(all_trams, all_tramlines);
        setRandomEvents(all_possible_events);
    }

    /**
     * Set sectors with stops based on prepared map
     */
    private static void setSectors(List<Sector> all_sectors, List<Stop> all_stops) throws IOException, JSONException {
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tram_map.json");
        ResourceHandler.addSectorsAndStops(arr, all_sectors, all_stops);
    }

    /**
     * Set tramlines based on prepared schedule
     */
    private static void setTramlines(List<Sector> all_sectors, List<Tramline> all_tramlines) throws IOException, JSONException{
        JSONArray arr = ResourceHandler.getJSONArrayFromFile("src/main/resources/tramlines.json");
        ResourceHandler.addTramlines(arr, all_tramlines, all_sectors);
    }

    /**
     * Spawn trams based on tramlines
     */
    private static void spawnTrams(List<Tram> all_trams, List<Tramline> all_tramlines){
        Tramline tramline;
        for (Tramline all_tramline : all_tramlines) {
            tramline = all_tramline;
            all_trams.add(new Tram(tramline, 0));
            all_trams.add(new Tram(tramline, 1));
        }
    }

    private static void setRandomEvents(List<RandomEvent> all_possible_events){
        int a=1;
        double b=20;
        //event = new RandomEvent(a,b);
    }
}
