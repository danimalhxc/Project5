import java.awt.*;

/**
 * Holds health and location of a mushroom
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-20
 **/
public class Mushroom{
    /** Small grid location of the mushroom **/
    public Point loc;
    
    /** Health of the mushroom **/
    public int health;
    
    /**
     * @param loc    Small grid location of the mushroom
     * @param health Initial health of the mushroom
     **/
    public Mushroom(Point loc, int health){
        // TODO: Initialize the location and health of the Mushroom from
        // TODO: the passed parameters.
    	this.loc = loc;
    	this.health = health;
    }
}
