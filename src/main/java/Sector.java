import java.util.ArrayList;
import java.util.List;

public class Sector extends Repairable {
    public Stop stop;
    public List<Tram> trams_on = new ArrayList<>();
    public List<Sector> connected_sectors = new ArrayList<>();
    private int capacity;

    public boolean has_space(int direction) {
        int count = 0;
        for(int i = 0; i < trams_on.size(); i++){
            if( trams_on.get(i).get_direction() == direction ){
                count++;
            }
        }
        if(count < capacity) { return true; }
        else { return false; }
    }

    public boolean has_stop(){
        if( this.stop != null ) { return  true; }
        else { return false; }
    }

    public Sector(int capacity, Stop stop){
        this.stop = stop;
        this.capacity = capacity;
    }

    public Sector(int capacity){
        this.capacity = capacity;
    }
}
