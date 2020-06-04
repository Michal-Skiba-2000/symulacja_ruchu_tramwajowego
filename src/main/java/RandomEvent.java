public class RandomEvent {
    public int duration;
    public int probability;

    public void setProbability(int input){
        this.probability=input;
    }
    public void setDuration(int input){
        this.duration=input;
    }

    public RandomEvent(int duration, int probability){
        this.duration = duration;
        this.probability = probability;
    }

}
