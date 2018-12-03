package project6;
/**
* This class creates an actor object with a string actor name input.
* @author Jenna Baruch * @version 10/02/2018
*/

public class Actor{
    String actorName;
/**
 * Actor class constructor
 * @param actorName to be set
 * @throws IllegalArgumentException if actorName is null or empty
 */
    public Actor(String actorName) throws IllegalArgumentException {
        this.actorName = actorName;

        //throw exception if actorName input is invalid
        if(actorName == null|| actorName.length()==0 ){
            throw new IllegalArgumentException("Actor name is expected");
        }
    }
/**
* Returns actor name for an actor object.
* @return string actorName.
*/
    public String name(){
        //return actorName as a string
        return actorName;
    }
}    