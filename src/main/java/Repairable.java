public abstract class Repairable {
    private RandomEvent random_event;
    private int time_to_repair;
    protected boolean is_active;

    public void repair(){
        if(this.is_active == true ){ return; }
        if (this.random_event != null){
            this.time_to_repair += random_event.duration;
            this.random_event = null;
        }
        this.time_to_repair -= 1;
        if (this.time_to_repair == 0){
            this.is_active = true;
        }
    }

    public void assign_event(RandomEvent event){
        this.random_event = event;
        this.is_active = false;
    }

    protected Repairable(){
        this.is_active = true;
        this.time_to_repair = 0;
    }
}
