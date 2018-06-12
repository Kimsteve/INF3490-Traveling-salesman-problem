
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class ExhaustiveSearch {
	
	
		

	public static void main (String args[])  throws Exception{

		TourManager tmanager = new TourManager();
		String fil = args[0];
		String [] citynames;
		String [] stringdistance;
	
		Scanner input = new Scanner(new File(fil));
		String city = input.nextLine();
		String delims = ";";

		citynames = city.split(delims);
		Double [][]  distance = new Double [citynames.length][citynames.length];
		for (int i =0;i< citynames.length; i++ ) {
		 	//System.out.println(citynames[i]);
		}
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

		Cities [] cities = new Cities [citynames.length];
		for (int k =0; k< distance.length ;k++ ) {
		 		Cities cty = new Cities(distance, k, citynames[k]);
			//System.out.println(distance[k][n]);
		 		 //DestinationsManager.addCity(cty);
		 		cities[k]=cty;
		 	
	 	}

	 	long starttime = System.currentTimeMillis(); // calculate time.
	    int numberOfpermutations =1;
	    double dist = 0;
	
	    
	    do {
	    	
	    	for (int n = 0; n < citynames.length ; n++ ) {
	    		if(n == citynames.length-1){
	    			dist += cities[n].distanceTo(n, 0);
	    		} else{ 
	    		 dist += cities[n].distanceTo(n, n+1);
	    		}
	    		
	    	}
           // System.out.println("Permutation number  " + numberOfpermutations + " " + Arrays.toString(cities) + "with distance  " +  dist);
	    	Tour tt = new Tour(dist, cities);
	    	tmanager.addTour(tt);
	    
	       numberOfpermutations++;
	    }
	    while ((cities= permutation(cities)) != null);


	 	System.out.println("The shortest tour is  " + tmanager.getTour(0).distance + "  km long  and the tour goes through  " +
	 	Arrays.toString(tmanager.getTour(0).cityN));

	 	long stoptime   = System.currentTimeMillis(); // stop timing
		long timetaken =  stoptime - starttime;
		System.out.println("Time taken to complete the task   : " + timetaken  +  "  millisecond");
 
	}

	// swap
	public static void swap(Cities [] ctty, int i, int j) {
    	Cities t= ctty[i];
    	ctty[i]= ctty[j];
        ctty[j]= t;
	}
 

	// permutation
	// rearraning the cities in all possible orders.
	public static Cities [] permutation(Cities [] ctty) {
     
	    int i, j;
	     
	    for (i= ctty.length-1; --i >= 0;)
	         if (ctty[i].compareTo(ctty[i+1])<0)
	             break;
	     
	    if (i < 0) return null;
	     
	    for (j= ctty.length; --j > i;)
	        if (ctty[i].compareTo(ctty[j])<0)
	            break;
	     
	    swap(ctty, i, j);
	     
	    for (j= ctty.length; ++i < --j;)
	        swap(ctty, i, j);
	     
	    return ctty;
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
    public double distanceTo(int from, int to){
       double d = distance[from][to];
       return d;
    }
    // get index
    public int getIndex(){
        return index;
    }
    
    @Override
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

Cities [] cityN;
Double distance;
 

	Tour(Double distance, Cities [] cityN){
		this.cityN = cityN;
		this.distance = distance;
	}
}

class TourManager {
	  private static ArrayList<Tour> shortestour = new ArrayList<Tour>();

	 public static void addTour(Tour tt) {
	        shortestour.add(tt);
	    }
	public static Tour getTour(int index) {
	      return  (Tour)shortestour.get(index);

	}

}




