import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;

public class TramTest {
    @Test
    public void moveTest() throws IOException, JSONException {
        GameState gameState = new GameState();
        Presimulation.presimualationSetup(gameState);
        Tram tram;
        LocalTime time = LocalTime.of(5, 0);
        int size;
        for(Tramline tramline: gameState.all_tramlines){
            size = tramline.sectors.size();
            tram = new Tram(tramline, 0, tramline.sectors.get(size-1));
            for(int i = 0; i < size-1; i++){ tram.makeMove(time, gameState); }
            assertTrue("Valid descending moving", tram.sector_on == tramline.sectors.get(0) && tram.getDirection() == 0);
            tram.makeMove(time, gameState);
            assertTrue("Valid direction reverse", tram.getDirection() == 1);
            tram = new Tram(tramline, 1, tramline.sectors.get(0));
            for(int i = 0; i < size-1; i++){ tram.makeMove(time, gameState); }
            assertTrue("Valid ascending moving", tram.sector_on == tramline.sectors.get(size-1) && tram.getDirection() == 1);
            tram.makeMove(time, gameState);
            assertTrue("Valid direction reverse", tram.getDirection() == 0);
        }
    }
}
