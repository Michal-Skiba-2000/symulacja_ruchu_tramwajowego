public abstract class ARepairable {
    protected RandomEvent randomEvent;
    protected int timeToRepair;
    protected boolean isActive;

    public abstract void repair();
    public abstract void assignEvent(RandomEvent event);


    protected ARepairable(){
        this.isActive = true;
        this.timeToRepair = 0;
    }
}
