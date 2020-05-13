import java.util.ArrayList;
import java.util.List;

public class Sector extends Repairable {
    public Stop stop;
    public List<Tram> trams_on = new ArrayList<>();
    public List<Sector> connected_sectors = new ArrayList<>();
    private int capacity;

    public boolean has_space() {
        if(trams_on.size() < capacity) { return true; }
        else { return false; }
    }

    public boolean has_stop(){
        if( stop != null ) { return  true; }
        else { return false; }
    }

    public Sector(Stop stop, List<Sector> connected, int capacity){
        this.stop = stop;
        this.connected_sectors = connected;
        this.capacity = capacity;
    }
}
