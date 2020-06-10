public class RandomEvent {
    public String name;
    public int id;
    public int duration;
    public double probability;

    public RandomEvent(int duration, double probability,String name,int id){
        this.duration = duration;
        this.name =name;
        this.probability = probability;
        this.id = id;
    }
}
