import java.util.ArrayList;
import java.util.List;

public class Sector extends Repairable {
    public Stop stop;
    public List<Tram> tramsOn = new ArrayList<>();
    private final int capacity;
    private final int id;

    /**
     * @param direction int value that specify direction of tram
     * @return          boolean value that specify if sector has space for Tram object
     */
    public boolean hasSpace(int direction) {
        int count = 0;
        for (Tram tram : tramsOn) {
            if (tram.getDirection() == direction) {
                count++;
            }
        }
        return count < capacity+5;
    }

    /**
     * @return boolean value that specify if sector has connected stop
     */
    public boolean hasStop(){
        return this.stop != null;
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
