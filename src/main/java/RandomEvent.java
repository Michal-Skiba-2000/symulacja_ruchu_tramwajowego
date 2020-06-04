public class RandomEvent {
    public double duration;
    public double probability;

    public void setProbability(double input){
        this.probability=input;
    }
    public void setDuration(int input){
        this.duration=input;
    }

    public RandomEvent(int duration, double probability){
        this.duration = duration;
        this.probability = probability;
    }

}
