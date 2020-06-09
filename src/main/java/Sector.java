import java.util.ArrayList;
import java.util.List;

public class Sector extends Repairable {
    public Stop stop;
    public List<Tram> trams_on = new ArrayList<>();
    public List<Sector> connected_sectors = new ArrayList<>();
    private final int capacity;
    private int id;

    /**
     * @param direction int value that specify direction of tram
     * @return          boolean value that specify if sector has space for Tram object
     */
    public boolean hasSpace(int direction) {
        int count = 0;
        for (Tram tram : trams_on) {
            if (tram.get_direction() == direction) {
                count++;
            }
        }
        return count < capacity+5;
    }

    /**
     * @return boolean value that specify if sector has connected stop
     */
    public boolean hasStop(){
        if(this.stop == null) return false;
        else return true;
    }

    /**
     * @return the id of the sector
     */
    public int getId(){ return id; }

    /**
     * @param capacity  capacity value
     * @param stop      Stop object or null if there is no stop connected to this sector
     * @param id        id of sector
     */
    public Sector(int capacity, Stop stop, int id){
        this.stop = stop;
        this.capacity = capacity;
        this.id = id;
    }
}
