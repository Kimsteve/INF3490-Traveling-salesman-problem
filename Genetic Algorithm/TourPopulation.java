public class TourPopulation {

    TourRoutes[] possibleroutes; //population of possible tours.

    // constructor
    public TourPopulation(int populationLength, boolean init) {
        possibleroutes = new TourRoutes[populationLength];
        // If true, create tour routes
        if (init) {
            // create tour routes
            for (int i = 0; i < populationLength(); i++) {
                TourRoutes tt = new TourRoutes(); 
                tt.generateTours();
                addRoute(i, tt);
            }
        }
    }
        
    // Get a route
    public TourRoutes getRoute(int index) {
        return possibleroutes[index];
    }

    public int populationLength() {
        return possibleroutes.length;
    }
   
   //add to possible routes
    public void addRoute(int index, TourRoutes tr) {
        possibleroutes[index] = tr;
    }


    // get the best route by loop thro all possible tours.
    public TourRoutes getFittest() {

        TourRoutes bestRoute = possibleroutes[0];
        
        for (int i = 1; i < populationLength(); i++) {
            if (bestRoute.getFitness() <= getRoute(i).getFitness()) {
                bestRoute = getRoute(i);
            }
        }
        return bestRoute;
    }    
}
