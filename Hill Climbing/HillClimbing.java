
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class HillClimbing {	

	public static void main (String args[])  throws Exception {

		String fil = args[0]; // file
		String [] citynames;
		String [] stringdistance; // reading distance as strings
	
		Scanner input=new Scanner(new File(fil));
		String name = input.nextLine();
		String delims = ";";

		citynames = name.split(delims); // city names
		// distance in km 
		Double [][]  distance = new Double [citynames.length][citynames.length];

		int r = 0;

		while(input.hasNext()){
		 		String inName=input.nextLine();

			 	stringdistance = inName.split(delims);

				for (int j =0;j< stringdistance.length; j++ ) {

				Double var= Double.parseDouble(stringdistance[j]);
				distance[r][j] = var;
			
			 }
			 r++;
		}

		// array to hold all the cities
		Cities [] cities = new Cities [citynames.length];
		for (int k =0; k< distance.length ;k++ ) {
		 		Cities cty = new Cities(distance, k, citynames[k]);
		 		cities[k]=cty;
		 	
	 	}	 	

	 	int ran = (int)(Math.random()*distance.length);
	 	int ran2 = (int)(Math.random()*distance.length);

	 	swap(cities, ran, ran2);

	 	Cities [] finl = findShortestTour(cities);
	 	double tour = tourlength(finl);

        System.out.println("Distance " + tour);
	 		
	 	System.out.println(Arrays.toString(finl));
 
	}

	// swap cities in a route
	public static void swap(Cities [] s, int fr, int to) {
    	Cities t= s[fr];
    	s[fr]= s[to]; 
    	s[to]= t;

    
	} 


	// calcuate the length of a tour.
	public static double tourlength (Cities [] ct) {
		double dist =0.0;

		for (int n = 0; n < ct.length ; n++ ) {
	    		if(n == ct.length-1){
	    			// last town to the town where tour started
	    			dist += ct[n].distanceTo(n, 0);
	    		} else{ 
	    			// distance from current to next
	    			 dist += ct[n].distanceTo(n, n+1);
	    		}
	    		
	    	}
	    	return dist;

	}

	//find the change in distance cause by swapping two pairs of cities i, j
	// get the highest decrease in the distance caused by swap.
	public static Cities [] findShortestTour (Cities [] city ){
        
		boolean distanceChange = true;
		double in_tDistance = tourlength(city); // initial value
		double decreaseInDistance  = 0.0;
		Map<int[], Double> allPossibleValues = new HashMap<int[], Double>();

		while(distanceChange){

			for (int i = 0;i < city.length; i++ ) {
				for (int j = 0; j <city.length ; j++ ) {

	 				// swap pairs i, j
					swap(city, i ,j);					

					int [] tempIndex = {i, j}; // array will act as a key for the hashmap
				
					double newlength = tourlength(city); // get the lenght of the tour after swap of i,j

					allPossibleValues.put(tempIndex, newlength); // add the values to hashmap

					swap(city, j, i);  // swap
					
				}
				
			}

			int [] changeA =  new int [2];
			double largestD = -200; // largest decrease

			// loop thro hashmap, get the distances associated with the key
			//check with initial distance
			// if change is > that largest decrease, assign that to largestD.
			for (int [] key: allPossibleValues.keySet()) {

				if (in_tDistance - allPossibleValues.get(key)> largestD) {

					if (in_tDistance - allPossibleValues.get(key) != decreaseInDistance) {
						changeA = key;

						largestD = in_tDistance - allPossibleValues.get(key);
						
					}
					
				}
				
			}
			
			if (decreaseInDistance>largestD) {
				distanceChange = false;
				break; 
			} else {
				largestD = decreaseInDistance;

				swap( city, changeA[0], changeA[1]);

				
			}		
		}
		return city;
	}	
}


class Cities implements Comparable <Cities> {
    String cityname;
    int index;
    Double [][] distance; 

    Cities(Double [][] distance, int index, String cityname){
        this.distance = distance;
        this.index = index;
        this.cityname = cityname;
    }    
    // Gets the distance to given city
    public double distanceTo(int x, int y){

        
        double d = distance[x][y];
       return d;
    }
   
   // return index of a city 
    public int getIndex(){
        return index;
    }
    @Override

    // return name
    public String toString(){
        return cityname;
    }

    //@Override

    public int compareTo(Cities cty) {
        if(this.index == cty.index)
            return 0;
        else
            return this.index > cty.index ? 1 : -1;
    }

}



class Tour {

Cities [] a;
Double distance;
 

	Tour(Double distance, Cities [] a){
		this.a = a;
		this.distance = distance;
	}
}





