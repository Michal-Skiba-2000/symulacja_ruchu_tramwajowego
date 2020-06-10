import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class PresimulationTest  {
    @Test
    public void initTest() throws IOException, JSONException  {
        GameState gameState = new GameState();
        Presimulation.presimualationSetup(gameState);
        assertTrue("Initial trams number", gameState.all_trams.size() == 80);
        assertTrue("Initial sectors number", gameState.all_sectors.size() == 389);
        assertTrue("Initial sectors to repair number", gameState.sectors_to_repair.size() == 0);
        assertTrue("Initial events number", gameState.all_possible_events.size() == 5);
        assertTrue("Initial tramlines number", gameState.all_tramlines.size() == 20);
        assertTrue("Initial passengers number", gameState.all_passengers.size() == 0);
        assertTrue("Initial stops number", gameState.all_stops.size() == 164);
    }

    @Test
    public void stopTest() throws IOException, JSONException  {
        GameState gameState = new GameState();
        Presimulation.presimualationSetup(gameState);
        for(Stop stop: gameState.all_stops){
            assertTrue("Nonull sector for stop", stop.sector != null);
        }
    }
}
