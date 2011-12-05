import java.math.*;
import java.awt.*;

/**
 * Contains settings shared throughout the program
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.6
 * @since   2011-08-08
 **/
public class Settings{
    // Centipede directions
    public static final int UP = -1;
    public static final int DOWN = 1;
    public static final int RIGHT = 1;
    public static final int LEFT = -1;
    
    // Overlap results
    public static final int NONE = 0;
    public static final int WALL = 1;
    public static final int PROJECTILE = 2;
    public static final int SHIP = 4;
    public static final int SHROOM = 8;
    public static final int CENT = 16;
    
    // Display dimensions
    public static final int width = 30;
    public static final int height = 30;
    public static final int scale = 20;
    
    // High score settings
    public static final String highScoresFileName = "highScores.txt";
    public static final int numScores = 10;
    
    // Game settings
    public static final Point shipStartLoc = new Point((int) Math.floor(width/2)*scale, (height-2)*scale);
    public static final int centDelayEasy = 100;
    public static final int centDelayMedium = 50;
    public static final int centDelayHard = 25;
    public static final int startDifficulty = centDelayMedium;
    public static final double levelFactor = 1.1;
    public static final int startLevel = 0;    
    public static final int centipedeStartSize = 10;      
            
    // Variable settings
    public static int centDelay = startDifficulty;
    public static int startShrooms = 20;
    public static int shroomStartHealth = 3;
    public static int invulnerableTime = 2000;
    public static int shipVerticalRange = 4;
    public static int maxProjectiles = 4;
    public static int startLives = 2;
    public static boolean superLaser = false;
    public static int superLaserDelay = 100;
}