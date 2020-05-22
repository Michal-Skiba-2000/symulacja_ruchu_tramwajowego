import java.util.ArrayList;
import java.util.List;

public class Passenger {
    public Stop end_stop;
    private Stop start_stop;
    //public Tram tram_on;
    private List<Stop> all_stops = new ArrayList<>();

    private Stop set_end_stop(){
        int rand;
            do {
                rand= (int) (Math.round(Math.random()*1000)%all_stops.size());
                end_stop=all_stops.get(rand);
            }while(end_stop==start_stop  );
        return end_stop;
    }

    public Passenger(Stop start_stop,List<Stop> stop_list){
        this.start_stop = start_stop;
        all_stops.addAll(stop_list);
        this.end_stop = set_end_stop();
    }
}
