public class RandomEvent {
    public String name;
    public int id;
    public int duration;
    public double probability;

    public void setProbability(int input){
        this.probability=input;
    }
    public void setDuration(int input){
        this.duration=input;
    }

    public RandomEvent(int duration, double probability,String name,int id){
        this.duration = duration;
        this.name =name;
        this.probability = probability;
        this.id = id;
    }
}
