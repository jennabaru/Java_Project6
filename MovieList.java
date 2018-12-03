package project6;

import java.util.ArrayList;
import java.util.Collections;
/**
* This class extends ArrayaList<Movie>. It has methods to search
* for matching keywords as well as a method to find a movie
* in database.
*
* @author Jenna Baruch * @version 10/02/2018
*/
public class MovieList extends ArrayList<Movie> {
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

        int i = 0;
        for (i=0;i<size(); i++) {
            if (get(i).title().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
        }
        //if there are no results
        if(results.size()==0){
            return null;
        }
        //call compareTo to sort results
        Collections.sort(results);
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
        int i = 0;
        for (i=0;i<size(); i++) {
            if (get(i).getActor1() != null && get(i).getActor1().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
            if (get(i).getActor2() != null && get(i).getActor2().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
            if (get(i).getActor3() != null && get(i).getActor3().name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
        }
        //if there are no results
        if(results.size()==0){
            return null;
        }
        //call compareTo to sort results
        Collections.sort(results);
        //return results movielist
        return results;
    }
/**
 * This method finds an equal movie
 * @param movie movie object
 * @return move if it is in database, null if it doesn't exist in database
 */
    public Movie find(Movie movie) {

        int i;
        for (i=0;i<size(); i++) {
            if (get(i).equals(movie)) {
                return get(i);
            }
        }
        return null;
    }
}