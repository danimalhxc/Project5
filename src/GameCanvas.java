import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Calls methods to draw the game display and handles mouse events within the game display
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.6
 * @since   2011-08-08
 **/
public class GameCanvas implements MouseListener, MouseMotionListener{
    /** This is what all the graphics will be drawn on **/
    public Canvas c;
    
    /** Provides the game level, score and lives for display.  Also provides whether the game is paused or over.  **/
    public GameLogic myGameLogic;
    
    /** Array of mushrooms **/
    private Mushroom myMushrooms[][];
    
    /** Array of centipedes **/
    private Centipede myCentipedes[];
    
    /** Array of projectiles **/
    private Point myProjectiles[];
    
    /** Used to play game sounds **/
    private GameSounds myGameSounds;
    
    /** Provides information about player's ship **/
    private Ship myShip;
    
    /** Holds the cursor to display on the canvas **/
    private BufferedImage cursorImg;
    
    /** Blank cursor used when the mouse is in the ship's valid movement range **/
    private Cursor blankCursor;
    
    /**
     * @param inGameSounds  Used to play game sounds
     * @param inMushrooms   Array of mushrooms
     * @param inCentipedes  Array of centipedes
     * @param inShip        Player's ship object
     * @param inProjectiles Array of projectiles
     **/
    public GameCanvas(GameSounds inGameSounds, Mushroom inMushrooms[][], Centipede inCentipedes[], Ship inShip, Point inProjectiles[]){
        myGameSounds = inGameSounds;
        myMushrooms = inMushrooms;
        myCentipedes = inCentipedes;
        myShip = inShip;
        myProjectiles = inProjectiles;
        
        // Transparent 16 x 16 pixel cursor image.
        cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        
        // Create a new blank cursor.
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        
        // Setup the canvas. Canvas is drawn with two extra spaces at the bottom for game status display
        c = new Canvas();
        c.setSize(Settings.width*Settings.scale, (Settings.height+2)*Settings.scale);
        c.setVisible(true);
        
        // Add MouseListener and MouseMotionListener
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }
    
    /**
     * Calls methods to draw images on the canvas
     **/
    public void drawFrame() {
        
        BufferStrategy bf = c.getBufferStrategy();
        Graphics g = null;
        
        try {
            g = bf.getDrawGraphics();
            g.setColor(Color.BLACK);
            
            // Canvas is drawn with two extra spaces at the bottom for game status display
            g.fillRect(0, 0, Settings.width*Settings.scale, (Settings.height+2)*Settings.scale);
            
            DrawGraphics.drawProjectiles(g, myProjectiles);            
            DrawGraphics.drawMushrooms(g, myMushrooms);               
            DrawGraphics.drawCentipedes(g, myCentipedes);            
            DrawGraphics.drawText(g, myShip, myGameLogic);
            
            // Only draw the ship if game is not over
            if (!myGameLogic.gameOverFlag || (myShip.invulnerableTime != 0)){
                DrawGraphics.drawShip(g, myShip);
            }
            
            // Dispose of the graphics object after it has been drawn
        }finally{
            g.dispose();
        }
        
        // Makes the next buffer visible by copying it to memory
        bf.show();
        
        // Make sure that the display is up-to-date before continuing
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Unused
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseClicked(MouseEvent e){
    }
    
    /**
     * Unused
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseEntered(MouseEvent e){
    }
    
    /**
     * Unused
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseExited(MouseEvent e){
    }
    
    /**
     * If the mouse button is pressed and the game is unpaused, try to fire the projectile
     * 
     * @param e MouseEvent that occured
     **/
    public void mousePressed(MouseEvent e){
        
        // Do not fire if the game is paused
        if (!myGameLogic.paused){
            
            // If the game is over, the ship should not fire
            if (myGameLogic.gameOverFlag){
                myShip.firing = false;
                
                // If using the super laser, turn on automatic firing
            }else if (Settings.superLaser){
                myShip.firing = true;
                
                // Otherwise, turn off automatic ship firing and try to fire a single projectile
            }else{
                myShip.firing = false;
                myShip.fire();
            }
        }
    }
    
    /**
     * If the mouse button is released and the game is unpaused, turn off automatic firing
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseReleased(MouseEvent e){
        // If ship null, don't even bother.
        if (!myGameLogic.paused){
            myShip.firing = false;
        }
    }
    
    /**
     * If the mouse is moved, the game is unpaused and not over, then try to move the ship
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseMoved(MouseEvent e){
        int tryX = e.getX();
        int tryY = e.getY();
        Cursor nextCursor = blankCursor;
        
        // If ship null, don't even bother.
        // Only perform logic if game is unpaused and not over
        if ((!myGameLogic.paused) && (!myGameLogic.gameOverFlag)){
            
            // Test for movement range bounds.  If beyond bounds, change cursor to a cursor.  If in bounds, change cursor to invisible.
            if (tryX < 0){
                myShip.tryLoc.x = 0;
                nextCursor = Cursor.getDefaultCursor();
            }else if (tryX > (Settings.width - 1)*Settings.scale){
                myShip.tryLoc.x = (Settings.width - 1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else{
                myShip.tryLoc.x = tryX;
            }
            
            if (tryY < (Settings.height-Settings.shipVerticalRange-1)*Settings.scale){
                myShip.tryLoc.y = (Settings.height-Settings.shipVerticalRange-1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else if (tryY > (Settings.height-1)*Settings.scale){
                myShip.tryLoc.y = (Settings.height-1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else{
                myShip.tryLoc.y = tryY;
            }
            
            // Otherwise, set the cursor to the default cursor    
        }else{
            nextCursor = Cursor.getDefaultCursor();
        }
        
        // Set the cursor on the game canvas to that decided by logic above
        c.setCursor(nextCursor);
    }
    
    /**
     * Same as mouseMoved.  Lets the user move the ship while holding the mouse.  Useful for super laser.
     * 
     * @param e MouseEvent that occured
     **/
    public void mouseDragged(MouseEvent e){    
        int tryX = e.getX();
        int tryY = e.getY();
        Cursor nextCursor = blankCursor;
        
        // Only perform logic if game is unpaused and not over
        if ((!myGameLogic.paused) && (!myGameLogic.gameOverFlag)){
            
            // Test for movement range bounds.  If beyond bounds, change cursor to a cursor.  If in bounds, change cursor to invisible.
            if (tryX < 0){
                myShip.tryLoc.x = 0;
                nextCursor = Cursor.getDefaultCursor();
            }else if (tryX > (Settings.width - 1)*Settings.scale){
                myShip.tryLoc.x = (Settings.width - 1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else{
                myShip.tryLoc.x = tryX;
            }
            
            if (tryY < (Settings.height-Settings.shipVerticalRange-1)*Settings.scale){
                myShip.tryLoc.y = (Settings.height-Settings.shipVerticalRange-1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else if (tryY > (Settings.height-1)*Settings.scale){
                myShip.tryLoc.y = (Settings.height-1)*Settings.scale;
                nextCursor = Cursor.getDefaultCursor();
            }else{
                myShip.tryLoc.y = tryY;
            }
            
            // Otherwise, set the cursor to the default cursor    
        }else{
            nextCursor = Cursor.getDefaultCursor();
        }
        
        // Set the cursor on the game canvas to that decided by logic above
        c.setCursor(nextCursor);
    }
}
