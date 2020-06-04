import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Passenger {
    public Stop end_stop;
    private final Stop start_stop;
    public LocalTime spawnTime =null;
    public LocalTime loadTime = null;
    private final GameState gameState;

    private Stop setFinalStop(){
        int line = (int) (Math.round(Math.random() * 1000)) % gameState.all_tramlines.size();
        if(gameState.all_tramlines.get(line).stops.contains(start_stop)){
            int rand = (int) (Math.round(Math.random() * 1000)) % gameState.all_tramlines.get(line).stops.size();
            end_stop=gameState.all_tramlines.get(line).stops.get(rand);
        }

        return end_stop;
    }

    public Passenger(Stop start_stop,GameState gs){
        gameState=gs;
        this.start_stop = start_stop;
        start_stop.passengers_on.add(this);
        while(end_stop==start_stop){
            this.end_stop = setFinalStop();
        }
    }
}
