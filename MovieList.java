package project6;

import java.util.ArrayList;
import java.util.Collections;
/**
* This class extends BST<Movie>. It has methods to search
* for matching keywords as well as a method to find a movie
* in database.
*
* @author Jenna Baruch * @version 12/05/2018
*/
public class MovieList extends BST<Movie> {
    /**
     * Default constructor
     */
    public MovieList() {
    }
/**
 * This method finds titles that contain the keyword parameter
 * @param keyword string title keyword
 * @return returns MovieList results
 */
    public MovieList getMatchingTitles (String keyword) {
        //if invalid keyword
        if (keyword == null || keyword.length() == 0) {
            return null;
        }
        // Search the the list of movies and see what contains the keywords
        MovieList results = new MovieList();

        //int i = 0;
        for (Movie obj : this) {
            if (obj.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(obj);
            }
        }
        //if there are no results
        if(results.size()==0){
            return null;
        }
        //call compareTo to sort results
        //Collections.sort(results);
        //return results movielist
        return results;
        
    }
/**
 * This method finds actors that contain the keyword parameter
 * @param keyword string actor keyword
 * @return returns MovieList results
 */
    public MovieList getMatchingActor ( String keyword ) {
        //if keyword is invalid
        if (keyword == null || keyword.length() == 0) {
            return null;
        }
        //create new Movielist
        MovieList results = new MovieList();
        //int i = 0;
        for (Movie current : this) {
            if (current.getActor1() != null && current.getActor1().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(current);
            }
            if (current.getActor2() != null && current.getActor2().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(current);
            }
            if (current.getActor3() != null && current.getActor3().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(current);
            }
        }
        //if there are no results
        if(results.size()==0){
            return null;
        }
        //return results movielist
        return results;
    }
/**
 * This method finds an equal movie
 * @param movie movie object
 * @return move if it is in database, null if it doesn't exist in database
 */
    public Movie find(Movie movie) {

        //int i;
        for (Movie current : this) {
            if (current.equals(movie)) {
                return current;
            }
        }
        return null;
    }
}