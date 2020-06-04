public class RandomEvent {
    public int duration;
    public double probability;

    public void setProbability(int input){
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
