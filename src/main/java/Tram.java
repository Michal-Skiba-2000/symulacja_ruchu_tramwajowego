import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Tram extends Repairable {
    public Sector sector_on;
    private final Tramline tramline;
    private final List<Passenger> passengers_on = new ArrayList<>();
    private int direction; // 0 - previous sector on list, 1 - next sector on list

    /**
     * Returns a Sector object from tramline.sectors list
     * based on sector_on and direction. The Sector is
     * whether a next one after sector_on on tramline.sectors
     * or a previous one
     *
     * @return the Sector object that the tram is moving on
     */
    private Sector get_sector_to_move_on(){
        int index_of_sector = tramline.sectors.indexOf(sector_on);
        Sector move_on_sector;

        if(direction == 0){
            if(index_of_sector == 0){
                direction = 1;
                move_on_sector = tramline.sectors.get(index_of_sector+1);
            } else{
                move_on_sector = tramline.sectors.get(index_of_sector-1);
            }
        } else{
            if(index_of_sector == tramline.sectors.size() - 1){
                direction = 0;
                move_on_sector = tramline.sectors.get(index_of_sector-1);
            } else{
                move_on_sector = tramline.sectors.get(index_of_sector+1);
            }
        }

        return move_on_sector;
    }

    /**
     * Returns boolean value that specify
     * if tram and sector to move on are active.
     * If tram is inactive, this method will call repair()
     *
     * @param sector    the Sector object that tram is moving on
     * @return          the boolean value that specify if move is valid
     */
    private boolean check_if_active(Sector sector){
        if( !is_active ){ repair(); }
        return is_active && sector.is_active;
    }

    /**
     * Iterates trough passengers_on and removes from list
     * those whose end_stop is current stop
     *
     * @param gameState
     */
    private void leave_passengers(GameState gameState) {
        Passenger passenger;
        for (int i = passengers_on.size() - 1; i >= 0; i--) {
            passenger = passengers_on.get(i);
            if (passenger.end_stop == sector_on.stop) {
                passengers_on.remove(i);
            }
        }
    }

    /**
     * @param passenger Passenger object that is considered to be loaded
     * @return          the boolean value that specify if passenger can be loaded
     */
    private boolean can_be_loaded(Passenger passenger){
        if(tramline.sectors.contains(passenger.end_stop.sector)){
            int sector_index = tramline.sectors.indexOf(passenger.end_stop.sector);
            int current_sector_index = tramline.sectors.indexOf(sector_on);
            if(direction == 0 && sector_index < current_sector_index) { return true; }
            else if(direction != 0 && sector_index > current_sector_index) { return true; }
        }
        return false;
    }

    /**
     * Iterates through passengers on current stop,
     * loads those who can achieve their destination with this tram
     * and save the time of loading to gameState
     *
     * @param time      LocalTime object that specifies current time in simulation
     * @param gameState GameState object to track time of loading passenger
     */
    private void load_passengers(LocalTime time,GameState gameState) {
        List<Passenger> passengers_to_remove = new ArrayList<>();
        for (Passenger passenger: sector_on.stop.passengers_on){
            if(tramline.sectors.contains(passenger.end_stop.sector)){
                passengers_to_remove.add(passenger);
                passengers_on.add(passenger);
                gameState.all_passengers.remove(passenger);
                passenger.loadTime=LocalTime.of(time.getHour(),time.getMinute());
            }
        }

        sector_on.stop.passengers_on.removeAll(passengers_to_remove);

    }

    /**
     * If there is space on sector to move on and both
     * tram and sector to move on are active,
     * method handle tram move
     *
     * @param time      LocalTime object that specifies current time in simulation
     * @param gameState GameState object to track time of loading passenger
     */
    public void make_move(LocalTime time,GameState gameState){
        Sector move_on_sector = get_sector_to_move_on();
        if( !check_if_active(move_on_sector) ){ return; }
        if( !move_on_sector.hasSpace(direction) ){ return; }
        sector_on.trams_on.remove(this);
        sector_on = move_on_sector;
        sector_on.trams_on.add(this);
        if( sector_on.hasStop() ){
            load_passengers(time,gameState);
            leave_passengers(gameState);
        }
    }

    /**
     * @return current direction of tram
     */
    public int get_direction(){ return direction; }

    /**
     * @param tramline  specify the Tramline object that the tram is on
     * @param direction specify initial int value for direction
     * @param sector_on specify the Sector object to start on
     */
    public Tram(Tramline tramline, int direction, Sector sector_on){
        super();
        this.sector_on = sector_on;
        this.sector_on.trams_on.add(this);
        this.tramline = tramline;
        this.direction = direction;
    }
}