import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors = new ArrayList<>();
    public List<Stop> stops = new ArrayList<>();

    private void create_stops_list(){

    }

    public Tramline(List<Sector> sectors){
        this.sectors = sectors;
        create_stops_list();
    }
}
