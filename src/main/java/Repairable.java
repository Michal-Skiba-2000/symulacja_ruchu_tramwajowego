public class Repairable extends ARepairable {
    /**
     * Handle self repairing.
     * Reduce time_to_repair by one
     * and set is_active to true if the object is repaired
     */
    public void repair(){
        if(this.isActive){ return; }
        if (this.randomEvent != null ){
            this.timeToRepair += randomEvent.duration;
            this.randomEvent = null;
        }
        this.timeToRepair -= 1;
        if (this.timeToRepair == 0){
            this.isActive = true;
        }
    }

    /**
     * Assign event to the object and inactivate the object
     * @param event
     */
    public void assignEvent(RandomEvent event){
        this.randomEvent = event;
        this.isActive = false;
    }

    protected  Repairable(){super();}
}
