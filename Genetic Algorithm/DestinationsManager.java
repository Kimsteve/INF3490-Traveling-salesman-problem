import java.util.ArrayList;

public class DestinationsManager {

    // arraylist to store cities
    private static ArrayList cities = new ArrayList<Cities>();

    // Add city to arraylist
    public static void addCity(Cities city) {
        cities.add(city);
    }
    
    // get a city
    public static Cities getCity(int index){
        return (Cities)cities.get(index);
    }
    
    //calculate number of cities
    public static int getTotalNumber(){
        return cities.size();
    }
}