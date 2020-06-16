import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors;
    public List<Stop> stops = new ArrayList<>();

    /**
     * Create List of Stops based on
     * data loaded from file
     */
    private void createStopsList(){
        for(Sector sector: sectors){
            if(sector.stop != null){
                stops.add(sector.stop);
            }
        }
    }

    /**
     * @param sectors list of sectors Tramline contains
     */

    public Tramline(List<Sector> sectors){
        this.sectors = sectors;
        createStopsList();
    }
}