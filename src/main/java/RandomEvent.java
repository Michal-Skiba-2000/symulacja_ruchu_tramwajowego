public class RandomEvent {
    public int duration;
    public double probability;

    public void set_probability(double input){
        this.probability=input;
    }
    public void set_duration(int input){
        this.duration=input;
    }

    public RandomEvent(int duration, double probability){
        this.duration = duration;
        this.probability = probability;
    }

}
