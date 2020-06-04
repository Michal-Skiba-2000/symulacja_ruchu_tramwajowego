import java.util.ArrayList;
import java.util.List;

public class Sector extends Repairable {
    public Stop stop;
    public List<Tram> trams_on = new ArrayList<>();
    public List<Sector> connected_sectors = new ArrayList<>();
    private final int capacity;
    private int id;

    public boolean hasSpace(int direction) {
        int count = 0;
        for (Tram tram : trams_on) {
            if (tram.get_direction() == direction) {
                count++;
            }
        }
        return count < capacity;
    }

    public boolean hasStop(){
        return this.stop != null;
    }

    public int getId(){ return id; }

    public Sector(int capacity, Stop stop, int id){
        this.stop = stop;
        this.capacity = capacity;
        this.id = id;
    }

    public Sector(int capacity){
        this.capacity = capacity;
    }
}
