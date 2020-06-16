import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class PresimulationTest  {
    @Test
    public void initTest() throws IOException, JSONException  {
        GameState gameState = new GameState();
        Presimulation.presimualationSetup(gameState);
        assertTrue("Initial trams number", gameState.allTrams.size() == 80);
        assertTrue("Initial sectors number", gameState.allSectors.size() == 389);
        assertTrue("Initial sectors to repair number", gameState.sectorsToRepair.size() == 0);
        assertTrue("Initial events number", gameState.allPossibleEvents.size() == 5);
        assertTrue("Initial tramlines number", gameState.allTramlines.size() == 20);
        assertTrue("Initial passengers number", gameState.allPassengers.size() == 0);
        assertTrue("Initial stops number", gameState.allStops.size() == 164);
    }

    @Test
    public void stopTest() throws IOException, JSONException  {
        GameState gameState = new GameState();
        Presimulation.presimualationSetup(gameState);
        for(Stop stop: gameState.allStops){
            assertTrue("Nonull sector for stop", stop.sector != null);
        }
    }
}
