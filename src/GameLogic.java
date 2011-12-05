import java.awt.*;
import java.util.*;
import java.math.*;

/**
 * Handles most game logic
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-20
 **/
public class GameLogic extends Thread{
	/** Set to true to synchronously reset the game **/
	public boolean syncReset;

	/** True if the game is over **/
	public boolean gameOverFlag;

	/** Current game level **/
	public int level;

	/** Current game difficulty expressed as centipede delay **/
	public int difficulty;

	/** True if the game is paused **/
	public boolean paused;

	/** Current game score **/
	public int score;

	/** Used to change the Pause Game text in the frame **/
	public Frame myFrame;

	/** Keeps track of all projectiles **/
	private Point myProjectiles[];

	/** Used for the placement of Mushrooms during game initialization **/
	private Random generator;

	private GameSounds myGameSounds;
	private Centipede myCentipedes[];
	private Mushroom myMushrooms[][];
	private GameCanvas myGameCanvas;
	private Ship myShip;
	private HighScores myHighScores;    

	/**
	 * @param inGameSounds  Called to play sound effects
	 * @param inGameCanvas  Controls display of game objects
	 * @param inHighScores  Called to add to high scores
	 * @param inMushrooms   Array of mushrooms
	 * @param inCentipedes  Array of centipedes
	 * @param inShip        Player's ship
	 * @param inProjectiles Array of projectiles
	 **/
	public GameLogic(GameSounds inGameSounds, GameCanvas inGameCanvas, HighScores inHighScores, Mushroom inMushrooms[][], Centipede inCentipedes[], Ship inShip, Point inProjectiles[]){
		myGameCanvas = inGameCanvas;

		// TODO: Initialize the following with the passed parameters:
		// TODO: myGameSounds, myHighScores, myMushrooms, myCentipedes
		// TODO: myProjectiles, myShip
		myGameSounds = inGameSounds;
		myHighScores = inHighScores;
		myMushrooms = inMushrooms;
		myCentipedes = inCentipedes;
		myProjectiles = inProjectiles;
		myShip = inShip;


		// TODO: Create a random generator object named generator
		Random generator = new Random();

		// TODO: Initialize syncReset to false
		syncReset = false;

		// TODO: Initialize the difficulty to Settings.startDifficulty
		difficulty = Settings.startDifficulty;
	}

	/**
	 * Starts the GameLogic thread
	 **/
	public void run(){    
		// This sets up double buffering to smooth out the display
		myGameCanvas.c.createBufferStrategy(2);

		// The game logic will loop as long as the program is running
		while(true){
			// TODO: This should update the positions and status of Centipedes, projectiles,
			// TODO: mushrooms and the ship.  The loop should allow for synchronous resetting
			// TODO: triggered through a Boolean.  It should also not process any game logic
			// TODO: while the game is paused.  Game over and level up conditions will be
			// TODO: determined in this loop.

			// Update the canvas
			myGameCanvas.drawFrame();
		}
	}

	/**
	 * Initialize a new game
	 **/
	public void initGame(){        
		// TODO: Initialize the difficulty, level and lives to match the corresponding
		// TODO: values in Settings.
		difficulty = Settings.startDifficulty;
		level = Settings.startLevel;
		myShip.lives = Settings.startLives;

		// TODO: Initialize myShip.invulnerableTime and score to 0.
		myShip.invulnerableTime = 0;

		// TODO: Remove any leftover centipedes
		myCentipedes = null;

		// TODO: Remove any leftover projectiles
		myProjectiles = null;

		// TODO: Create a single centipede of Settings.centipedeStartSize segments heading
		// TODO: right and down.  Be sure to initialize all the segments of the Centipede.
		myCentipedes[0] = new Centipede(10, 0, 1);
		for (int i = 0; i < 10; i++) {
			myCentipedes[0].segments[i] = new Point(0, 0);
		}

		// TODO: Create array of mushrooms and randomly place them on the game grid.
		// TODO: Make sure that mushrooms do not overlap and keep them off of the 
		// TODO: bottom row.
		myMushrooms = new Mushroom[Settings.width][Settings.height];
		for (int i = 0; i < Settings.startShrooms; i++)	{
			int x = generator.nextInt(Settings.width);
			int y = generator.nextInt(Settings.height);
			Point p = new Point(x, y);
			myMushrooms[x][y] = new Mushroom(p, Settings.shroomStartHealth);
		}


		// TODO: Set the gameOverFlag to false and play the newGame sound
		gameOverFlag = false;
		myGameSounds.newGame();
	}

	/**
	 * Returns sum corresponding to overlapped objects
	 * 
	 * @param loc Unscaled grid coordinate
	 * @return Each potentially overlapping object has a value that is a power of two
	 * These values are summed up and then returned to the calling method.
	 * The calling method can then run a modulus to find exactly what was impacted.
	 * Refer to Settings.java for the return values.
	 **/
	private int overlap(Point loc){
		int result = Settings.NONE;

		// TODO: Check all segments of all centipedes to see if they contain the Point loc.
		for (int i = 0; i < myCentipedes.length; i++)	{
			for (int j = 0; j < myCentipedes[i].segments.length; j++)	{
				if (myCentipedes[i].segments[j] == loc)	{
					//TODO: Handle this situation
				}
			}
		}
		// TODO: Check all mushrooms to see if they contain the Point loc.


		// TODO: Check to see if the ship occupies the Point loc.
		if (myShip.loc == loc){
			//TODO: Handle this situation
		}

		// TODO: Check all projectiles to see if they contain the Point loc.
		for (int i = 0; i < myProjectiles.length; i++)	{
			//TODO: Handle this situation
		}

		// TODO: Check to see if the point has impacted or crossed beyond a wall.

		return result;
	}

	/**
	 * Moves the centipedes to follow the segment ahead of it
	 * 
	 * Centipedes will try to move its head to the position directly in front of it.
	 * It will then have all following segments change its position to that of the segment
	 * that was ahead of it.  If the head cannot move to its desired position, then it will
	 * move vertically and reverse its horizontal direction.  If at the top/bottom of the screen
	 * then reverse the vertical direction as well.  A centipede can share space with a mushroom
	 * but that mimics the behavior of the original game and is intended.
	 **/
	private void moveCentipedes(){
		// TODO: Loop through all centipedes and have them move according to the
		// TODO: project specifications.
	}

	/**
	 * Tries to move the ship according to its tryLoc provided by the GameCanvas
	 * 
	 * If the ship tries to move into a mushroom, its location will be set to a distance away
	 * from the mushroom equal to the game's scaling factor (Settings.scale).  A ship can slip
	 * between two diagonal mushrooms because of this.  If the ship tries to move into a centipede
	 * then it will lose a life, blow up and become invulnerable for Settings.invulnerableTime ms.
	 * If the ship loses its last life, then the game is over.
	 **/
	private void moveShip(){
		// TODO: Try moving the ship based on the mouse position.  First test to see if the ship has impacted any
		// TODO: Centipedes.  If it has, play the explosion sound, decrement the number
		// TODO: of lives and set the ship's invulnerableTime to Settings.invulnerableTime.
		// TODO: If the ship has run out of lives, run the gameOver() method.

		// TODO: Check if the ship is trying to move into a mushroom.  The ship should not
		// TODO: lose a life from this, but it should be blocked from moving.

		// TODO: Set the ship's new location based on the results of the tests above.
	}

	/**
	 * Moves the projectiles and checks for collision
	 * 
	 * Tries to move the projectiles vertically by one pixel.  If the destination overlaps something else,
	 * then remove the projectile and react appropriately to the impacted object.
	 **/
	private void moveProjectiles(){
		// TODO: Create a loop to move all projectiles.

		// TODO: If a projectile impacts a centipede, mushroom or wall, the projectile should be removed.

		// TODO: If the projectile impacted a Centipede, increase the score proportionately
		// TODO: to the difficulty and whether the user is using a superLaser or not.
		// TODO: The impacted Centipede segment will need to be removed and depending on
		// TODO: where the Centipede was impacted, it may need to split into two Centipedes.
		// TODO: Play the centDie sound.
		// TODO: Place a new Mushroom at the location where the Centipede was hit.

		// TODO: If the projectile impacted a Mushroom, decrement the Mushroom's health.
		// TODO: If the Mushroom's health is 0, remove the Mushroom.
		// TODO: Play the shroomHit sound.
	}

	/**
	 * Flips gameOverFlag to true, plays the game over sound and tries to add a high score
	 **/
	public void gameOver(){
		// TODO: Set gameOverFlag to true, stop the ship from firing, play the gameOver sound
		// TODO: and try adding the score to the high scores.    
	}
}