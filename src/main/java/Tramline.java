import java.util.ArrayList;
import java.util.List;

public class Tramline {
    public List<Sector> sectors;
    public List<Stop> stops = new ArrayList<>();
    private final int id;

    /**
     * @return id which is name of the tramline
     */
    public int getId(){ return id; }

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
     * @param id name for this Tramline
     */

    public Tramline(List<Sector> sectors, int id){
        this.sectors = sectors;
        createStopsList();
        this.id = id;
    }
}