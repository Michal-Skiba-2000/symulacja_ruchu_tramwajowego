public class Passenger {
    public Stop end_stop;
    public Stop start_stop;
    public Tram tram_on;
    private Tramlines tramlines;

    private Stop set_end_stop(){
        int rand;
            do {
                rand= (int) (Math.round(Math.random())%tramlines.all_tramlines.size());
                if(tramlines.all_tramlines.get(rand).stops.contains(start_stop)){
                    int stop = (int)Math.round(Math.random())%tramlines.all_tramlines.get(rand).stops.size();
                    end_stop = tramlines.all_tramlines.get(rand).stops.get(stop);
                }
            }while(end_stop!=start_stop);
        return end_stop;
    }

    public Passenger(Stop start_stop, Tramlines tramline){
        this.start_stop = start_stop;
        this.tramlines = tramlines;
        this.end_stop = set_end_stop();
    }
}
