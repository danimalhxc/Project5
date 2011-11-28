import java.awt.*;
import javax.swing.*;

/**
 * CS 180 - Project 5: Variation of classic Centipede arcade game
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.6
 * @since   2011-08-08
 **/
public class Project5{
    /** Plays sounds **/
    private GameSounds myGameSounds;
    
    /** Handles high scores **/
    private HighScores myHighScores;
    
    /** Displays main game screen and handle mouse input **/
    private GameCanvas myGameCanvas;
    
    /** Handles game logic **/
    private GameLogic myGameLogic;
    
    /** Frame with menu **/
    private Frame myFrame;
    
    /** Array of mushrooms **/
    private Mushroom myMushrooms[][];
    
    /** Array of centipedes **/
    private Centipede myCentipedes[];
    
    /** Player's ship **/
    private Ship myShip;
    
    /** Array of projectiles **/
    private Point myProjectiles[];
    
    /**
     * Starts the program
     * 
     * @param args Array of input strings.  Not used.
     **/
    public static void main(String[] args){
        new Project5();
    }
    
    public Project5(){
        // TODO: Create new objects myGameSounds, myHighScores, myMushrooms,
        // TODO: myProjectiles, myShip, myCentipedes, myGameCanvas, myGameLogic, myFrame       
        
        // TODO: Set myGameCanvas.myGameLogic to myGameLogic
        // TODO: Set myHighScores.myFrame to myFrame
        // TODO: Set myGameLogic.syncReset to true
        
        // TODO: Start myGameLogic and then wait for it to join
    }
}
