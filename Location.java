package project6;
/**
* This class creates a location object with a
* string location and string funFact input.
* @author Jenna Baruch * @version 10/02/2018
*/
public class Location{
    String location;
    String funFact;

/**
 * Location class constructor
 * @param location location to be set
 * @param funFact funFact to be set
 * @throws IllegalArgumentException if location is null or empty
 */
    public Location(String location, String funFact) throws IllegalArgumentException{
        this.location=location;
        this.funFact=funFact;

        //throw exception if location input is invalid
        if(this.location == null || this.location.length()==0){
            throw new IllegalArgumentException("Location is expected");
        }
    }
/**
 * getLocation method gets location as string from a location object
 * @return string location
 */
    public String getLocation() {
        return this.location;
    }
/** 
 * getFunFact method gets funFact as a string from a location object
 * @return string funFact
 */
    public String getFunFact() {
        return this.funFact;
    }
}