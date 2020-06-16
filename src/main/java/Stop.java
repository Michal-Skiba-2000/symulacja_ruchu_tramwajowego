import java.util.ArrayList;
import java.util.List;

public class Stop {
    public List<Passenger> passengersOn = new ArrayList<>();
    public Sector sector;
    public String name;

    public void addSector(Sector sector){
        this.sector = sector;
    }

    public Stop(String name){
        this.name = name;
    }

}
