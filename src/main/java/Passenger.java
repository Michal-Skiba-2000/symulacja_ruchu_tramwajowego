import java.time.LocalTime;

public class Passenger {
    public Stop endStop;
    private final Stop startStop;
    public LocalTime spawnTime =null;
    public LocalTime loadTime = null;
    private final GameState gameState;

    /**
     * Returns randomize objct endstop which is contained,
     * by tramline that stops on start_stop.
     * @return endstop
     */
    private Stop setFinalStop(){
        Stop stop = null;
        int line = (int) (Math.round(Math.random() * 10000)) % gameState.allTramlines.size();
        if(gameState.allTramlines.get(line).stops.contains(startStop)){
            int rand = (int) (Math.round(Math.random() * 1000)) % gameState.allTramlines.get(line).stops.size();
            stop=gameState.allTramlines.get(line).stops.get(rand);
        }

        return stop;
    }

    /**
     * @param start_stop starting point randomized by Simulation
     * @param gs current game state
     */

    public Passenger(Stop start_stop, GameState gs){
        gameState=gs;
        this.startStop = start_stop;
        start_stop.passengersOn.add(this);
        do{
            this.endStop = setFinalStop();
        }while(this.endStop==start_stop || this.endStop == null );
    }
}