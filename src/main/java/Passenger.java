import java.time.LocalTime;

public class Passenger {
    public Stop end_stop;
    private final Stop start_stop;
    public LocalTime spawnTime =null;
    public LocalTime loadTime = null;
    private final GameState gameState;

    private Stop setFinalStop(){
        Stop endstop = null;
        int line = (int) (Math.round(Math.random() * 10000)) % gameState.all_tramlines.size();
        if(gameState.all_tramlines.get(line).stops.contains(start_stop)){
            int rand = (int) (Math.round(Math.random() * 1000)) % gameState.all_tramlines.get(line).stops.size();
            endstop=gameState.all_tramlines.get(line).stops.get(rand);
        }

        return endstop;
    }

    public Passenger(Stop start_stop, GameState gs){
        gameState=gs;
        this.start_stop = start_stop;
        start_stop.passengers_on.add(this);
        do{
            this.end_stop = setFinalStop();
        }while(this.end_stop==start_stop || this.end_stop == null );
    }
}
