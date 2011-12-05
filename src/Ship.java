import java.awt.*;

/**
 * Holds information about the player's ship and handles firing of projectiles
 * 
 * @author              James Wilkinson <jhwilkin@purdue.edu>
 * @version             1.7
 * @since               2011-11-20
 **/
public class Ship{
    /** Position that the ship is trying to move into **/
    public Point tryLoc;
    
    /** Actual position of the ship **/
    public Point loc;
    
    /** Number of lives that the ship has **/
    public int lives;
    
    /** Used by the super laser.  True if firing. **/
    public boolean firing;
    
    /** Time (ms) left for the ship to be invulnerable **/
    public int invulnerableTime;
    
    /** Array of projectiles **/
    private Point myProjectiles[];
    
    /** Time (ms) left for the ship to be invulnerable **/
    private GameSounds myGameSounds;
    
    /**
     * @param inGameSounds  Used to generate sound when ship fires
     * @param inProjectiles Used to create new projectiles
     **/
    public Ship(GameSounds inGameSounds, Point[] inProjectiles){
        // TODO: Initialize myProjectiles and myGameSounds with the
        // TODO: passed parameters.
    	myGameSounds = inGameSounds;
    	myProjectiles = inProjectiles;
        
        // TODO: Set the loc of the ship to (Settings.shipStartLoc.x, Settings.shipStartLoc.y)
        loc = new Point(Settings.shipStartLoc.x, Settings.shipStartLoc.y);
    	
        // TODO: Set tryLoc to the same location as the ship's starting position.
        tryLoc = new Point(loc);
        
        // TODO: Set the ship's lives to Settings.startLives
        lives = Settings.startLives;
        
        // TODO: Initialize invulnerableTime to 0.
        invulnerableTime = 0;
        
    }
    
    /**
     * Try to fire a projectile from the ship
     * 
     * Fires a projectile from the ship if the ship is not invulnerable and there are less than Settings.maxProjectiles projectiles.
     **/
    public void fire(){
        // TODO: Write code to implement the description above.
        // TODO: Make sure to play the appropriate sound effect if the ship fires.
    	if (invulnerableTime == 0 && myProjectiles.length < Settings.maxProjectiles)	{
    		myGameSounds.laser();
    		
    	}
    }
}
