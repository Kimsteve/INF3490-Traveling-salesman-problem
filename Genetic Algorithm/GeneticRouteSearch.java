public class GeneticRouteSearch {

    
    private static final int tournamentSize = 7;   // size of tournament
    private static final double mutationRate = 0.01; // rate at which various mutations occur over time.
    private static final boolean elitism = true; // best from current is carried over to next gen

    public static TourPopulation generate(TourPopulation pop) {
        TourPopulation gen = new TourPopulation(pop.populationLength(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            gen.addRoute(0, pop.getFittest());
            elitismOffset = 1;
        }

        // creating new pop from current
        for (int i = elitismOffset; i < gen.populationLength(); i++) {
            // Select parents
            TourRoutes firstparent = tournamentSelection(pop);
            TourRoutes secondparent = tournamentSelection(pop);
            // Crossover parents
            TourRoutes child = crossover(firstparent, secondparent);
            // add new child to the tourpopulation
            gen.addRoute(i, child);
        }

        // mutate
        for (int i = elitismOffset; i < gen.populationLength(); i++) {
            mutate(gen.getRoute(i));
        }

        return gen;
    }

      // candidate selection
    private static TourRoutes tournamentSelection(TourPopulation pop) {
        // Create tournament
        TourPopulation tournament = new TourPopulation(tournamentSize, false);
        //generate random candidates
        for (int i = 0; i < tournamentSize; i++) {

            int randomIndex = (int) (Math.random() * pop.populationLength());

            tournament.addRoute(i, pop.getRoute(randomIndex));
        }
        // get the fittest route
        TourRoutes fittest = tournament.getFittest();
        return fittest;
    }


	// mutate
	// by interchanging the position/index of cities in the route.
    private static void mutate(TourRoutes route) {
       
        for(int index=0; index < route.routeLength(); index++){
            // mutation rate
            if(Math.random() < mutationRate){
                // get another route index
                int index2 = (int) (route.routeLength() * Math.random());

                // get cities randomly generated city and the one from the loop
                Cities city1 = route.getIndexedCity(index);
                Cities city2 = route.getIndexedCity(index2);

                // Mutate by setting cities to different index/position in the route
                route.setCityToIndex(index2, city1);
                route.setCityToIndex(index, city2);
            }
        }

    }

    private static TourRoutes crossover (TourRoutes firstparent, TourRoutes secondparent){

    	TourRoutes crossoverchild = new TourRoutes();

        int startIndex = (int) (Math.random() * firstparent.routeLength());
        int endIndex = (int) (Math.random() * firstparent.routeLength());

        // first parent to crossoverchild
        for (int i = 0; i < crossoverchild.routeLength(); i++) {
            
            if (startIndex < endIndex && i > startIndex && i < endIndex) {
                crossoverchild.setCityToIndex(i, firstparent.getIndexedCity(i));
            } 
            else if (startIndex > endIndex) {
                if (!(i < startIndex && i > endIndex)) {
                    crossoverchild.setCityToIndex(i, firstparent.getIndexedCity(i));
                }
            }
        }

       // secondparent

        for (int j = 0; j < secondparent.routeLength(); j++) {
            // check if city is in the crossover child route
            if (!crossoverchild.containsCity(secondparent.getIndexedCity(j))) {
                // check if they are empty pos/index on the crossover child route
                for (int k = 0; k < crossoverchild.routeLength(); k++) {
                    // add if found
                    if (crossoverchild.getIndexedCity(k) == null) {
                        crossoverchild.setCityToIndex(k, secondparent.getIndexedCity(j));
                        break;
                    }
                }
            }
        }
        return crossoverchild;



    }

}