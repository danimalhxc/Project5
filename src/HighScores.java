import java.io.*;
import javax.swing.*;

/**
 * Displays and updates high scores
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-21
 **/
public class HighScores{
    /** Used to display high scores and name prompt on the main frame **/
    public Frame myFrame;
    
    private String names[];    
    private int scores[];
    
    public HighScores(){
        // TODO: Check if the file named in Settings.highScoresFileName exists.
        // TODO: If it does not, create it with Settings.numScores entires of
        // TODO: name = nobody and score = 0.  If it does exist, load its contents
        // TODO: into the names and scores arrays.
    }
    
    /**
     * Display high scores
     * 
     * @param name  Name of player to highlight
     * @param score Score of player to highlight
     **/
    public void showHighScores(String name, int score){
        // TODO: Display high scores and if an entry has the same name and
        // TODO: score as the passed parameters, highlight it with >>> <<<
    }
    
    /**
     * Add score to high scores if greater than lowest high score
     * 
     * @param score Score to try to add
     **/
    public void addHighScore(int score){
        // TODO: If the provided score is greater than the lowest high score,
        // TODO: then replace the lowest score with the new score and then
        // TODO: call the sortScores() method.
    }
    
    /**
     * Sort bottom score up to correct position in high scores table
     **/
    public void sortScores(){
        // TODO: Use a single round of bubble sort to bubble the last entry
        // TODO: in the high scores up to the correct location.
    }
    
    /**
     * Write scores to high scores file
     **/
    public void writeScores(){
        // TODO: Write the high scores data to the file name indicated
        // TODO: by Settings.highScoresFileName.
        try{
        	PrintWriter s = new PrintWriter(new File(Settings.highScoresFileName));
    	}catch(FileNotFoundException e){
    		System.out.println("Exception found: " + e);
    	}
    }
}
