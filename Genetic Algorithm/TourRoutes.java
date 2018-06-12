import java.util.ArrayList;
import java.util.Collections;

public class TourRoutes{

    // routes
    public  ArrayList routes = new ArrayList<Cities>();
    private double fitness = 0;
    private double distance = 0;
    
    // Constructor : generate tours and set to null.
    public TourRoutes(){
        for (int i = 0; i < DestinationsManager.getTotalNumber(); i++) {
            routes.add(null);
        }
    }
    
    public TourRoutes(ArrayList routes){
        this.routes = routes;
    }

        // Generate tours
    public void generateTours() {
        // 
        for (int i = 0; i < DestinationsManager.getTotalNumber
            (); i++) {
          setCityToIndex(i, DestinationsManager.getCity(i));
        }
        	//shuffle the tours
	        Collections.shuffle(routes);
    }

        // Route fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)calculateRouteDistance();
        }
        return fitness;
    }



   //get city a specified index.
    public Cities getIndexedCity(int index) {
        return (Cities)routes.get(index);
    }

    // set city to a specified index
    public void setCityToIndex(int index, Cities city) {
        routes.set(index, city);
 		// reset the following if routes order has changes
        fitness = 0;
        distance = 0;
    }

        // calculate number of cities in the route
    public int routeLength() {
        return routes.size();
    }
    
    // check if city is already in the route
    public boolean containsCity(Cities city){
        return routes.contains(city);
    }
    
    @Override
    public String toString() {
        // names of the cities in the route.
        String rnames = "";
        for (int i = 0; i < routeLength(); i++) {
            rnames += getIndexedCity(i)+"->";
        }
        return rnames;
    }


 	public double calculateRouteDistance(){
        if (distance == 0) {
            double route = 0;

            for (int i=0; i < routeLength(); i++) {
                
                Cities departure = getIndexedCity(i);
                Cities destination;             
            
                // check if at final city
                if(i +1 < routeLength()){
                    destination = getIndexedCity(i+1);
                }
                else{
                	// at destination
                    destination = getIndexedCity(0);
                }
                //get the index of the destination city
                int destinationIndex = destination.getIndex();
                // distance from departure city to destination
                route += departure.distanceTo(destinationIndex);

            }

            distance = route;
        }
        return distance;
    }  

}
