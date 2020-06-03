public abstract class ARepairable {
    protected RandomEvent random_event;
    protected int time_to_repair;
    protected boolean is_active;

    public abstract void repair();
    public abstract void assignEvent(RandomEvent event);

    protected ARepairable(){
        this.is_active = true;
        this.time_to_repair = 0;
    }
}
