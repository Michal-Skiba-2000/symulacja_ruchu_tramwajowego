public class Repairable extends ARepairable {

    public void repair(){
        if(this.is_active){ return; }
        if (this.random_event != null ){
            this.time_to_repair += random_event.duration;
            this.random_event = null;
        }
        this.time_to_repair -= 1;
        if (this.time_to_repair == 0){
            this.is_active = true;
        }
    }

    public void assignEvent(RandomEvent event){
        this.random_event = event;
        this.is_active = false;
    }

    protected  Repairable(){super();}
}
