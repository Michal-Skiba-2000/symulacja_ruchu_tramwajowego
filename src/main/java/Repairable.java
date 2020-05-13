public abstract class Repairable {
    private RandomEvent random_event;
    private int time_to_repair;
    protected boolean is_active;

    public void repair(){
        if(is_active == true ){ return; }
        if (random_event != null){
            time_to_repair += random_event.duration;
            random_event = null;
        }
        time_to_repair -= 1;
        if (time_to_repair == 0){
            is_active = true;
        }
    }

    public void assign_event(RandomEvent event){
        random_event = event;
        is_active = false;
    }

    protected Repairable(){
        is_active = true;
        time_to_repair = 0;
    }
}
