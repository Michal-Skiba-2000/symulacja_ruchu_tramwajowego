public class RandomEvent {
    public String name;
    public int id;
    public int duration;
    public double probability;

    /**
     * Creates new Event
     * @param duration duration of event loaded from file
     * @param probability probability of event loaded from file
     * @param name name of event loaded from file
     * @param id id of event loaded from file
     */
    public RandomEvent(int duration, double probability,String name,int id){
        this.duration = duration;
        this.name =name;
        this.probability = probability;
        this.id = id;
    }
}