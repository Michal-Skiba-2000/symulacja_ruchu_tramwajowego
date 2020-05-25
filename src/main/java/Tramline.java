import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors = new ArrayList<>();
    public List<Stop> stops = new ArrayList<>();
    private int id;

    public int getId(){ return id; }

    private void create_stops_list(){

    }

    public Tramline(List<Sector> sectors, int id){
        this.sectors = sectors;
        create_stops_list();
        this.id = id;
    }
}
