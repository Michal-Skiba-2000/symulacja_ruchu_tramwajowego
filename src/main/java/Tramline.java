import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors;
    public List<Stop> stops = new ArrayList<>();
    private int id;

    public int getId(){ return id; }

    private void createStopsList(){

    }

    public Tramline(List<Sector> sectors, int id){
        this.sectors = sectors;
        createStopsList();
        this.id = id;
    }
}
