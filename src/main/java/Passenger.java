

public class Passenger {
    public Stop end_stop;
    public Stop start_stop;
    public Tram tram_on;
    private Tramlines stan =new Tramlines();


    public Stop get_end_stop(){
        int rand;
            do {
                rand= (int) (Math.round(Math.random())%stan.all_tramlines.size());
                if(stan.all_tramlines.get(rand).stops.contains(start_stop)){
                    int stop = (int)Math.round(Math.random())%stan.all_tramlines.get(rand).stops.size();
                   end_stop.sector=stan.all_tramlines.get(rand).stops.get(stop);

                }
            }while(end_stop!=start_stop);





        return end_stop;
    }



}
