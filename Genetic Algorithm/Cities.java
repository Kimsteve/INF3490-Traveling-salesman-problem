public class Cities {
    String cityname;   
    int index;              // city indexes
    Double [][] distance; // the distance fbtw cities as read from the file

    Cities(Double [][]   distance, int index, String cityname){
        this.distance = distance;
        this.index = index;
        this.cityname = cityname;
    }    
    // Gets the distance to given city
    public double distanceTo(int y){
        
        double d = distance[index][y];
       return d;
    }
    
     public int getIndex(){
        return index;
    }
    @Override
    public String toString(){
        return cityname;
    }
}