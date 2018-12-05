package project6;

import java.util.ArrayList;
/**
* This class implements the comparable interface and
* provides parameters to create movie objects.
* It has addLocation, toString, equals, and compareTo
* methods.
* @author Jenna Baruch * @version 12/05s/2018
*/
public class Movie implements Comparable<Movie>{
    private String title;
    private int year;
    private ArrayList<Location> location = new ArrayList<Location>();
    private String director;
    private String writer;
    private Actor actor1;
    private Actor actor2;
    private Actor actor3;

/**
 * This constructor creates a movie object with title and year.
 * @param title string to be set as movie title
 * @param year int to be set as movie year
 * @throws IllegalArgumentException if title doesn't exist or year is invalid
 */
    public Movie(String title, int year) throws IllegalArgumentException{
        //throws exception if title is invalid
        if (title == null || title.length()==0){
            throw new IllegalArgumentException("Movie title expected");
        }
        //throws exception if year is invalid
        if(year<1900 || year>2020){
            throw new IllegalArgumentException("Invalid year. Year must be between 1990 and 2020");
        }
        this.title = title;
        this.year = year;
    }
/**
 * This constructor creates a movie object with title, year, director, writer,
 * and up to 3 actors.
 * @param title string to be set as title
 * @param year int to be set as year
 * @param director string to be set as director
 * @param writer string to be set as writer
 * @param actor1 actor object to be set as actor1
 * @param actor2 actor object to be set as actor2
 * @param actor3 actor object to be set as actor3
 * @throws IllegalArgumentException if title, year, or actor1 is invalid
 */
    public Movie(String title, int year, String director, String writer, 
                                                            Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException{
        //throws exception if title is invalid
        if (title == null || title.length()==0){
            throw new IllegalArgumentException("Movie title expected");
        }
        //throws exception if year is invalid
        if(year<1900 || year>2020){
            throw new IllegalArgumentException("Invalid year. Year must be between 1990 and 2020");
        }
        //throws exception if actor1 is null
        if (actor1 == null){
            throw new IllegalArgumentException("Movie must have an actor");
        }

        this.title = title;
        this.year = year;
        this.director = director;
        this.writer = writer;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    public String getTitle(){
        return title;
    }

    public Actor getActor1(){
        return actor1;
    }

    public Actor getActor2(){
        return actor2;
    }

    public Actor getActor3(){
        return actor3;
    }
/**
 * This method adds location to list of locations for current movie object
 * @param loc Location object
 * @throws IllegalArgumentException if location is null
 */
    public void addLocation(Location loc) throws IllegalArgumentException{
        //throws exception if location is null
        if (loc == null){
            throw new IllegalArgumentException("Invalid location");
        }
        //add given location to the list of filming locations for the current movie object
        this.location.add(loc);
    }
/**
 * This method overrides the toString method to print movie objects in
 * a desired display style.
 * @return string movieOutput
 */
    @Override
    public String toString() {
        System.out.println();
        //if there are 3 actors in movie
        if(actor2!= null && actor3!= null){
            //format movie output with 3 actors
            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter  \t: %s\nstarring\t: %s, %s, %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name(), actor2.name(), actor3.name());

            String locations = "";

            int i=0;
            for(i=0;i<location.size(); i++){
                //if there is a funFact with movie object
                if(location.get(i).getFunFact()!=null && (!(location.get(i).getFunFact().isEmpty()))){
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }
                //if there is no associated funFact
                else{
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }
            //concat strings
            movieOutput = movieOutput.concat(locations);
            //return string of correct movie display output
            return movieOutput;

        }
        //if there are 2 actors in a movie
        else if(actor2!=null){
            //format movie output with 2 actors
            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter\t\t: %s\nstarring\t: %s, %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name(), actor2.name());

            String locations = "";

            int i=0;
            for(i=0;i<location.size(); i++){
                //if there is a funfact
                if(location.size()==1 && location.get(i).getFunFact()!= null && (!(location.get(i).getFunFact().isEmpty()))){
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }
                //if there is no associated funFact
                else{
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }
            //concat strings
            movieOutput = movieOutput.concat(locations);
            //return string of correct movie display output
            return movieOutput;


        }
        //if there is only 1 actor in a movie
        else if (actor1!=null) {
            //format movie output with 1 actor
            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter\t\t: %s\nstarring\t: %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name());

            String locations = "";

            int i=0;
            //if there is a funFact
            if (location!=null && location.get(i).getFunFact()!= null && (!(location.get(i).getFunFact().isEmpty()))){
                for(i=0; i<location.size(); i++) {
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }
            }
            //if there is no associated funFact
            else{
                for(i=0; i<location.size(); i++) {
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }
            //concat strings
            movieOutput = movieOutput.concat(locations);
            //return string of correct movie display output
            return movieOutput;
        }
        return "";
    }
/**
 * This method calls
 * compareTo to see if two movie objects are equal.
 * @param movie movie object
 * @return true if movies are the same, false if they are different
 */
    public Boolean equals(Movie movie) {
        if (movie == null){
            return false;
        }
        if(movie.compareTo(this)==0){
            return true;
        }
        return false;
    }
/**
 * This method overrides the compareTo method and
 * compares two movie objects by year and title to
 * see if they are the same.
 * @return int: 0 if movies are same, 1 if movie year is greater
 * -1 if movie year is less 
 */
    @Override
    public int compareTo(Movie movie) {
        //if movies have the same year
        if (this.year == movie.year) {
            return this.title.toLowerCase().compareTo(movie.title.toLowerCase());
        } else if (this.year > movie.year) {
            return 1;
        } else {
            return -1;
        }
    }
}