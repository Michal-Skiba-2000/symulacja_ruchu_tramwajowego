import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Tram extends Repairable {
    public Sector sectorOn;
    private final Tramline tramline;
    private final List<Passenger> passengersOn = new ArrayList<>();
    private int direction; // 0 - previous sector on list, 1 - next sector on list

    /**
     * Returns a Sector object from tramline.sectors list
     * based on sector_on and direction. The Sector is
     * whether a next one after sector_on on tramline.sectors
     * or a previous one
     *
     * @return the Sector object that the tram is moving on
     */
    private Sector geSectorToMoveOn(){
        int indexOfSector = tramline.sectors.indexOf(sectorOn);
        Sector moveOnSector;

        if(direction == 0){
            if(indexOfSector == 0){
                direction = 1;
                moveOnSector = tramline.sectors.get(indexOfSector+1);
            } else{
                moveOnSector = tramline.sectors.get(indexOfSector-1);
            }
        } else{
            if(indexOfSector == tramline.sectors.size() - 1){
                direction = 0;
                moveOnSector = tramline.sectors.get(indexOfSector-1);
            } else{
                moveOnSector = tramline.sectors.get(indexOfSector+1);
            }
        }

        return moveOnSector;
    }

    /**
     * Returns boolean value that specify
     * if tram and sector to move on are active.
     * If tram is inactive, this method will call repair()
     *
     * @param sector    the Sector object that tram is moving on
     * @return          the boolean value that specify if move is valid
     */
    private boolean checkIfActive(Sector sector){
        if( !isActive ){ repair(); }
        return isActive && sector.isActive;
    }

    /**
     * Iterates trough passengers_on and removes from list
     * those whose end_stop is current stop
     *
     */
    private void leavePassengers() {
        Passenger passenger;
        for (int i = passengersOn.size() - 1; i >= 0; i--) {
            passenger = passengersOn.get(i);
            if (passenger.endStop == sectorOn.stop) {
                passengersOn.remove(i);
            }
        }
    }

    /**
     * Iterates through passengers on current stop,
     * loads those who can achieve their destination with this tram
     * and save the time of loading to gameState
     *
     * @param time      LocalTime object that specifies current time in simulation
     * @param gameState GameState object to track time of loading passenger
     */
    private void loadPassengers(LocalTime time,GameState gameState) {
        List<Passenger> passengersToRemove = new ArrayList<>();
        for (Passenger passenger: sectorOn.stop.passengersOn){
            if(tramline.sectors.contains(passenger.endStop.sector)){
                passengersToRemove.add(passenger);
                passengersOn.add(passenger);
                gameState.allPassengers.remove(passenger);
                passenger.loadTime=LocalTime.of(time.getHour(),time.getMinute());
            }
        }

        sectorOn.stop.passengersOn.removeAll(passengersToRemove);

    }

    /**
     * If there is space on sector to move on and both
     * tram and sector to move on are active,
     * method handle tram move
     *
     * @param time      LocalTime object that specifies current time in simulation
     * @param gameState GameState object to track time of loading passenger
     */
    public void makeMove(LocalTime time,GameState gameState){
        Sector moveOnSector = geSectorToMoveOn();
        if( !checkIfActive(moveOnSector) ){ return; }
        if( !moveOnSector.hasSpace(direction) ){ return; }
        sectorOn.tramsOn.remove(this);
        sectorOn = moveOnSector;
        sectorOn.tramsOn.add(this);
        if( sectorOn.hasStop() ){
            loadPassengers(time,gameState);
            leavePassengers();
        }
    }

    /**
     * @return current direction of tram
     */
    public int getDirection(){ return direction; }

    /**
     * @param tramline  specify the Tramline object that the tram is on
     * @param direction specify initial int value for direction
     * @param sector_on specify the Sector object to start on
     */
    public Tram(Tramline tramline, int direction, Sector sector_on){
        super();
        this.sectorOn = sector_on;
        this.sectorOn.tramsOn.add(this);
        this.tramline = tramline;
        this.direction = direction;
    }
}