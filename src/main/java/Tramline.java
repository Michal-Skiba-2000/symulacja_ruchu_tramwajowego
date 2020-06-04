import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors;
    public List<Stop> stops = new ArrayList<>();
    private final int id;

    public int getId(){ return id; }

    private void createStopsList(){
        for(Sector sector: sectors){
            if(sector.stop != null){
                stops.add(sector.stop);
            }
        }
    }

    public Tramline(List<Sector> sectors, int id){
        this.sectors = sectors;
        createStopsList();
        this.id = id;
    }
}
