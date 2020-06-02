import java.util.ArrayList;
import java.util.List;

public interface GameState {
    List<Tram> all_trams = new ArrayList<>();
    List<Sector> all_sectors = new ArrayList<>();
    List<Sector> sectors_to_repair = new ArrayList<>();
    List<RandomEvent> all_possible_events = new ArrayList<>();
    List<Tramline> all_tramlines = new ArrayList<>();
    List<Passenger> all_passengers = new ArrayList<>();
    List<Stop> all_stops = new ArrayList<>();
}
