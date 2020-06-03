import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Tram extends Repairable {
    public Sector sector_on;
    private final Tramline tramline;
    private final List<Passenger> passengers_on = new ArrayList<>();
    private int direction; // 0 - previous sector on list, 1 - next sector on list

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

    private boolean check_if_active(Sector sector){
        if( is_active ){ repair(); }
        return is_active && sector.is_active;
    }

    private void leave_passengers(Stop stop) {
        Passenger passenger;
        for (int i = passengers_on.size() - 1; i >= 0; i--) {
            passenger = passengers_on.get(i);
            if (passenger.end_stop == stop) {
                passengers_on.remove(i);
            }
        }
    }

    private boolean can_be_loaded(Sector sector){
        if(tramline.sectors.contains(sector)){
            int sector_index = tramline.sectors.indexOf(sector);
            int current_sector_index = tramline.sectors.indexOf(sector_on);
            if(direction == 0 && sector_index < current_sector_index) { return true;}
            else if (direction != 0 && sector_index > current_sector_index) { return true; }
            else return false;
        }
        return false;
    }

    private void load_passengers(Stop stop, LocalTime time) {
        Stop end_stop;
        Passenger passenger;
        for (int i = stop.passengers_on.size()-1; i >= 0; i--){
            passenger = stop.passengers_on.get(i);
            end_stop = passenger.end_stop;

            if( can_be_loaded(end_stop.sector) ){
                stop.passengers_on.remove(i);
                passengers_on.add(passenger);
                passenger.loadTime=time;
            }
        }
    }

    public void make_move(LocalTime time){
        Sector move_on_sector = get_sector_to_move_on();
        if( !check_if_active(move_on_sector) ){ return; }
        if( !move_on_sector.hasSpace(direction) ){ return; }
        sector_on = move_on_sector;
        if( sector_on.hasStop() ){
            Stop stop = sector_on.stop;
            leave_passengers(stop);
            load_passengers(stop,time);
        }
    }

    public int get_direction(){
        return direction;
    }

    public Tram(Tramline tramline, int direction){
        super();
        if(direction == 0){sector_on = tramline.sectors.get(tramline.sectors.size()-1);}
        else{sector_on = tramline.sectors.get(0);}
        sector_on.trams_on.add(this);
        this.tramline = tramline;
        this.direction = direction;
    }
}