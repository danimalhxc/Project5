import java.awt.*;
import java.util.*;

/**
 * Several methods to draw graphics
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.6
 * @since   2011-08-10
 **/
public class DrawGraphics{
    /** Random number generator for explosion effect **/
    private static Random generator = new Random();
    
    /**
     * Draws the game status text on the top of the screen
     * 
     * @param g Graphics object to be drawn on.
     **/
    public static void drawText(Graphics g, Ship myShip, GameLogic myGameLogic){
        String livesString = "Left: ";
        String scoreString = "Score: ";
        String levelString = "Level ";
        
        if (myShip != null) livesString += myShip.lives;
        if (myGameLogic != null){ 
            scoreString += myGameLogic.score;
            levelString += myGameLogic.level;
        }
        
        // Set the font style, color, weight and size
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, Settings.scale));
        
        // Draw the strings on the bottom of the canvas, under the play area
        g.drawString(scoreString, Settings.scale, (Settings.height+1)*Settings.scale);
        g.drawString(levelString, Settings.scale*((Settings.width - levelString.length())/2 + 2), (Settings.height+1)*Settings.scale);
        g.drawString(livesString, Settings.scale*(Settings.width - livesString.length()/2 - 1), (Settings.height+1)*Settings.scale);
    }
    
    /**
     * Draws the mushrooms
     * 
     * @param g Graphics object to be drawn on.
     **/
    public static void drawMushrooms(Graphics g, Mushroom myMushrooms[][]){
        int startAngle, arcAngle;
        
        // Loop through all the mushroom columns
        for (int i = 0; i < myMushrooms.length; i++){
            
            // Loop through all the mushroom rows on this column
            for (int j = 0; j < myMushrooms[i].length; j++){
                
                // Only draw the mushroom if it exists
                if (myMushrooms[i][j] != null){
                    
                    // Draw the mushroom stem
                    g.setColor(Color.GRAY);
                    g.fillRect((int) Math.round((myMushrooms[i][j].loc.x + 0.3)*Settings.scale), (int) Math.floor((myMushrooms[i][j].loc.y + 0.5)*Settings.scale), (int) Math.floor(Settings.scale*0.4), (int) Math.floor(Settings.scale*0.5));
                    
                    // Draw the mushroom cap
                    g.setColor(Color.RED);
                    g.fillArc(myMushrooms[i][j].loc.x*Settings.scale, myMushrooms[i][j].loc.y*Settings.scale, Settings.scale, Settings.scale, 0, 180);
                    
                    // Draw some circles on the mushroom cap
                    g.setColor(Color.YELLOW);
                    g.fillOval((int) Math.round((myMushrooms[i][j].loc.x + 0.1)*Settings.scale), (int) Math.round((myMushrooms[i][j].loc.y + 0.2)*Settings.scale), Settings.scale/5, Settings.scale/5);
                    g.fillOval((int) Math.round((myMushrooms[i][j].loc.x + 0.4)*Settings.scale), (int) Math.round((myMushrooms[i][j].loc.y + 0.1)*Settings.scale), Settings.scale/5, Settings.scale/5);
                    g.fillOval((int) Math.round((myMushrooms[i][j].loc.x + 0.7)*Settings.scale), (int) Math.round((myMushrooms[i][j].loc.y + 0.2)*Settings.scale), Settings.scale/5, Settings.scale/5);
                    
                    // If the mushroom has less than full health, erase wedges from it by drawing black arcs over the mushroom that was just drawn
                    g.setColor(Color.BLACK);
                    switch (myMushrooms[i][j].health){
                        case 1:
                            g.fillArc(myMushrooms[i][j].loc.x*Settings.scale, (int) Math.round((myMushrooms[i][j].loc.y + 0.2)*Settings.scale), Settings.scale, Settings.scale, 45, -270);
                            break;
                        case 2:
                            g.fillArc(myMushrooms[i][j].loc.x*Settings.scale, (int) Math.round((myMushrooms[i][j].loc.y + 0.2)*Settings.scale), Settings.scale, Settings.scale, -45, -90);
                            break;
                    }
                }
            }
        }
    }
    
    /**
     * Draws the centipedes
     * 
     * @param g Graphics object to be drawn on.
     **/
    public static void drawCentipedes(Graphics g, Centipede myCentipedes[]){
        int i, j;
        
        // Vary the color of the centipede based on its speed.  When the centipede delay is the same as the easiest difficulty, the head should
        // be yellow and the body should be red.  As the delay approaches 0, the head color should approach cyan and the body color should approach blue.
        Color head = new Color(255*Settings.centDelay/Settings.centDelayEasy, 255, 255*(1 - Settings.centDelay/Settings.centDelayEasy));
        Color body = new Color(255*Settings.centDelay/Settings.centDelayEasy, 0, 255*(1 - Settings.centDelay/Settings.centDelayEasy));
        
        // Loop through all centipedes
        for(i = 0; i < myCentipedes.length; i++){
            // Only draw the centipede if it exists and has segments
            if ((myCentipedes[i] != null) &&
                (myCentipedes[i].length != 0)){
                // First draw the head
                g.setColor(head);
                g.fillOval(myCentipedes[i].segments[0].x*Settings.scale, myCentipedes[i].segments[0].y*Settings.scale, Settings.scale, Settings.scale);
                
                // Next draw the body segments
                g.setColor(body);
                for (j = 1; j < myCentipedes[i].length; j++){
                    g.fillOval(myCentipedes[i].segments[j].x*Settings.scale, myCentipedes[i].segments[j].y*Settings.scale, Settings.scale, Settings.scale);
                }
            }
        }
    }
    
    /**
     * Draws the ship
     * 
     * @param g Graphics object to be drawn on.
     **/
    public static void drawShip(Graphics g, Ship myShip){
        int i;
        
        // Only draw the ship if it is not invulnerable
        if (myShip.invulnerableTime == 0){
            
            // The ship is represented by a green triangle
            g.setColor(Color.GREEN);
            int[] xPoints = {myShip.loc.x,myShip.loc.x+Settings.scale/2,myShip.loc.x+Settings.scale};
            int[] yPoints = {myShip.loc.y+Settings.scale,myShip.loc.y,myShip.loc.y+Settings.scale};
            g.drawPolygon(xPoints, yPoints, 3);
            
        // If the game is invulnerable, it was recently blown up and so an explosion animation should be displayed
        }else{
            
            // Draw as many pixels as the width of the ship
            for (i = 0; i < Settings.scale; i++){
                
                // Randomly pick a color, yellow or red
                switch (generator.nextInt(2)){
                    case 0:
                        g.setColor(Color.YELLOW);
                        break;
                    default:
                        g.setColor(Color.RED);
                        break;
                }
                
                // Draw a particle (oval) 3 pixels high and wide at a random Y position corresponding to
                // where the ship is at.  The X position of the particle is an offset relative to the ship's location.
                g.fillOval(myShip.loc.x+i, myShip.loc.y+Settings.scale*generator.nextInt(10)/10, 3, 3);
            }
        }
    }
    
    /**
     * Draws the projectiles
     * 
     * @param g Graphics object to be drawn on.
     **/
    public static void drawProjectiles(Graphics g, Point myProjectiles[]){
        int i;
        
        // Loop through each projectile slot
        for (i = 0; i < myProjectiles.length; i++){
            
            // Only draw the projectile if it exists
            if (myProjectiles[i] != null){
                
                // If super laser is on, draw a blue laser
                if (Settings.superLaser){
                    g.setColor(Color.BLUE);
                    g.fillRect(myProjectiles[i].x, myProjectiles[i].y, Settings.scale/5, Settings.scale);
                    
                // Otherwise, draw a yellow pellet
                }else{
                    g.setColor(Color.YELLOW);
                    g.fillOval(myProjectiles[i].x, myProjectiles[i].y, Settings.scale/5, Settings.scale/5);
                }
            }
        }
    }
}