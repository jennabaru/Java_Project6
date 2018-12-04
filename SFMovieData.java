package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;   
import java.util.Arrays;
/**
* This class houses the main program. Here the user interacts
* with the program to enter title and actor keywords. A list of
* the movies with those keywords are then displayed to the user. 
* This is run on a loop until the user quits the program. This class
* also opens the file to read in data.
*
* @author Jenna Baruch * @version 10/02/2018
*/
public class SFMovieData{
    /**
     * The main method checks for correct command line input and
     * creates new movie objects for data in text file. Uses scanner
     * to ask user for keywords, searches database, and prints movie objects.
     * @param args command line argument with data file input
     */
    public static void main(String[] args){

        File movieFile = null;
        //if there is no command line input
        if (args.length == 0){
            System.err.println("Usage Error: The name of a file is expected as the argument");
            System.exit(1);
        }

        // Create a new MovieList to store all the Movies from the CSV file
        MovieList movieDB = new MovieList();
        
        try {
            movieFile = new File(args[0]);
            Scanner movies = new Scanner(movieFile);

            // Skip past the headers in the CSV file
            movies.nextLine();
            ArrayList<String> csvMovie = null; 

            while (movies.hasNextLine()) {
                String movieString = movies.nextLine();
                csvMovie = splitCSVLine(movieString);
                
                //if data file is empty
                if (csvMovie == null){
                    System.err.println("Error: movie database is empty");
                    System.exit(1);
                }
                // create a movie 
                if (csvMovie.size() < 9) {
                    continue;
                }

                //call 2 param constructor to create new movie
                Movie movie = new Movie(csvMovie.get(0), Integer.parseInt(csvMovie.get(1)));
                //check to see if movie already exists
                Movie existingMovie = movieDB.find(movie);
                
                //initialize actor objects
                Actor actor1 = null;
                Actor actor2 = null;
                Actor actor3 = null;

                //if movie does not exist
                if (existingMovie == null) {
                    // Use the full constuctor to create a movie and add it to the MovieList
                    if (csvMovie.size() >= 9 && csvMovie.get(8).length() > 0){
                        actor1 = new Actor(csvMovie.get(8));
                    }
                    if (csvMovie.size() >= 10  && csvMovie.get(9).length() > 0 ){
                        actor2 = new Actor(csvMovie.get(9));
                    }
                    if (csvMovie.size() >= 11 && csvMovie.get(10).length() > 0) {
                        actor3 = new Actor(csvMovie.get(10));
                    }

                    // If the first actor is blank, skip adding the movie
                    if (actor1 != null) {
                        if(csvMovie.get(2)!=null && !csvMovie.get(2).equals("")){
                            Movie newMovie =  new Movie(csvMovie.get(0), Integer.parseInt(csvMovie.get(1)), csvMovie.get(6), csvMovie.get(7), 
                                actor1, actor2, actor3);
                            //call location constructor
                            if(csvMovie.get(2)!= null && csvMovie.get(2).length() > 0) {
                                Location loc = new Location(csvMovie.get(2), csvMovie.get(3));
                                newMovie.addLocation(loc);
                            }
                        //add new movie to the database
                        movieDB.add(newMovie);  
                        }
                        
                    }
                } else {
                    // add the new location to the existing movie object
                    if(csvMovie.get(2)!= null && csvMovie.get(2).length() > 0) {
                        Location loc = new Location(csvMovie.get(2), csvMovie.get(3));
                        existingMovie.addLocation(loc);

                    }
                }
            }
        //catch file not found exception
        } catch(FileNotFoundException e) {
            System.err.println("Error: the file "+movieFile.getAbsolutePath()+" does not exist.\n");
		    System.exit(1);
        }  
    // Start the Console Program and loop until the user quits
    Boolean userQuit = false;
    //call method to print welcome message
    welcomeBanner();

    while(!userQuit){
       Scanner cmdScanner = new Scanner (System.in);
       
       System.out.println("Enter your search query: ");
       System.out.println();

       while(cmdScanner.hasNextLine()){
           String cmd[] = cmdScanner.nextLine().trim().split(" ");
           //if user enters incorrect input
           if(cmd.length == 0 || (cmd.length==1 && !cmd[0].equalsIgnoreCase("quit"))){
               System.out.println();
               System.err.println("Usage Error: program expects title keyword, actor keyword, or quit. Try again");
               System.out.println();
               System.out.println();
               break;

           } else if (cmd[0].equalsIgnoreCase("title")) {
                //Search for title keyword
                String keywords = "";
                int i = 0;
                for(i=1; i<cmd.length; i++) {
                    keywords = keywords.concat(cmd[i]);
                    keywords = keywords.concat(" ");
                } 
                keywords = keywords.trim();
                MovieList titleResults = movieDB.getMatchingTitles(keywords);

                //if there are no results
                if(titleResults==null){
                    System.out.println();
                    System.out.println("No results, try again");
                    System.out.println();
                    System.out.println();
                }
                //if there are results, call toString method to print
                else{
                    for(Movie current : titleResults){
                        System.out.println(current.toString());
                        System.out.println();
                    }
                }

                System.out.println("Enter your search query: ");
                System.out.println();

           } else if (cmd[0].equalsIgnoreCase("actor")) {  
                //Search for actor keyword
                String keywords = "";
                int i = 0;
                for(i=1; i<cmd.length; i++) {
                    keywords = keywords.concat(cmd[i]);
                    keywords = keywords.concat(" ");
                } 
                keywords = keywords.trim();

                MovieList actorResults = movieDB.getMatchingActor(keywords);
                //if there are no results
                if(actorResults== null){
                    System.out.println("No results, try again");
                }
                //call toString method to display results as output
                else {
                    for (Movie current : actorResults) {
                        System.out.println(current.toString());
                    }
                }

                System.out.println("Enter your search query: ");
                System.out.println();
           }
           else {
                //if user eneters quit
                if (cmd[0].equalsIgnoreCase("quit")){
                   userQuit=true;
                   break;
               }
           }
       }
    }
}
/**
 * Void method prints program welcome output.
 */
    public static void welcomeBanner(){
        System.out.println("Search the database by matching keywords to titles or actor names.");
        System.out.println("\tTo search for matching titles, enter title KEYWORD");
        System.out.println("\tTo search for matching actor names, enter actor KEYWORD");
        System.out.println("\tTo finish the program, enter quit");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    
    /**
    * Splits the given line of a CSV file according to commas and double quotes
    * (double quotes are used to surround multi-word entries so that they may contain commas)
    * @author Joanna Klukowska
    * @param textLine  a line of text to be passed
    * @return an Arraylist object containing all individual entries found on that line
    */
    public static ArrayList<String> splitCSVLine(String textLine){
        if (textLine == null ) return null;
        ArrayList<String> entries = new ArrayList<String>(); 
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer(); 
        char nextChar;
        boolean insideQuotes = false; 
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) { 
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"'|| nextChar == '\u201C' || nextChar =='\u201D') {

                //change insideQuotes flag when nextChar is a quote 
                if (insideQuotes) {
                    insideQuotes = false; insideEntry = false;
                }else {
                    insideQuotes = true;
                    insideEntry = true; }
            } else if (Character.isWhitespace(nextChar)) { 
                if ( insideQuotes || insideEntry ) {
                // add it to the current entry
                    nextWord.append( nextChar );
                }else { // skip all spaces between entries 
                    continue;
                }
            } else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry 
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false; 
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer(); 
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar); insideEntry = true;
            }
        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list 
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }
        return entries;
    }
}