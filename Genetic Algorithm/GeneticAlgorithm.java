
import java.io.File;
import java.util.Scanner;
import java.util.Scanner;

import java.util.Arrays;

public class GeneticAlgorithm {
	
	
		

	public static void main (String args[])  throws Exception{
		 String fil = args[0]; // file
		 String [] citynames; // names of the cities
		 String [] stringdistance;
		

		 Scanner input=new Scanner(new File(fil));
		 String city = input.nextLine();
		 String delims = ";";

		citynames = city.split(delims);
		Double [][]  distance = new Double [citynames.length][citynames.length]; // distance btw cities.
		
		int r = 0;
		while(input.hasNext()){
		 		String inName=input.nextLine();

			 	stringdistance = inName.split(delims);

				for (int j =0;j< stringdistance.length; j++ ) {

				Double var= Double.parseDouble(stringdistance[j]);
				distance[r][j] = var;  // store distance values.
			
			 }
			 r++;
	 
		 }

		for (int k =0; k< distance.length ;k++ ) {
		 	Cities cty = new Cities(distance, k, citynames[k]);
		 	DestinationsManager.addCity(cty);
		 			 	
	 	}

	 	//initiliaze population
	 	//GenericRouteSearch gg = GenericRouteSearch();
	 	long starttime = System.currentTimeMillis(); // start time

	 	int [] populationsize = {50, 100, 150};

	 	for (int pp = 0;pp<populationsize.length ;pp++ ) {

		 	TourPopulation tp = new TourPopulation(populationsize[pp], true);
		 	//System.out.println("Initial distance: " + tp.getFittest().calculateRouteDistance());

		 	tp = GeneticRouteSearch.generate(tp);
		 	for (int i = 0; i < 100; i++) {
	            tp = GeneticRouteSearch.generate(tp);
	        }
	        System.out.println("population size is: " + populationsize[pp]);

	        System.out.println("Shortest distance: " + tp.getFittest().calculateRouteDistance());
	        System.out.println("Route: ");
	        System.out.println(tp.getFittest());

	 	}


	 	System.out.println();
	 	long stoptime   = System.currentTimeMillis(); // stop timing
		long timetaken =  stoptime - starttime;
		System.out.println("Time taken to complete the task   : " + timetaken  +  "  millisecond");
 
		
	 	System.out.println();

	 	TourRoutes tt = new TourRoutes();
	 	int toursInspected= tt.routes.size();
	 	System.out.println("Number of tours inspected :  " + toursInspected);
	 	
	}
}