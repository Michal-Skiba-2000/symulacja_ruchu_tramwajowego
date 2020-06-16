import java.util.ArrayList;
import java.util.List;

public class GameState {
    List<Tram> allTrams = new ArrayList<>();
    List<Sector> allSectors = new ArrayList<>();
    List<Sector> sectorsToRepair = new ArrayList<>();
    List<RandomEvent> allPossibleEvents = new ArrayList<>();
    List<Tramline> allTramlines = new ArrayList<>();
    List<Passenger> allPassengers = new ArrayList<>();
    List<Stop> allStops = new ArrayList<>();
}
