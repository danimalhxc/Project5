import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Plays game sounds
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.6
 * @since   2011-08-08
 **/
public class GameSounds{
    
    Clip cannon;
    Clip laser;
    Clip shipExplode;
    Clip centDie;
    Clip shroomHit;
    Clip newGame;
    Clip nextLevel;
    Clip gameOver;
        
    public GameSounds(){
        
        URL url;
        AudioInputStream audioIn;
        
        try{
            // Cannon        
            url = this.getClass().getClassLoader().getResource("cannon.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            cannon = AudioSystem.getClip();
            cannon.open(audioIn);
            
            // laser        
            url = this.getClass().getClassLoader().getResource("laser.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            laser = AudioSystem.getClip();
            laser.open(audioIn);
            
            // shipExplode        
            url = this.getClass().getClassLoader().getResource("shipExplode.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            shipExplode = AudioSystem.getClip();
            shipExplode.open(audioIn);
            
            // centDie        
            url = this.getClass().getClassLoader().getResource("centDie.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            centDie = AudioSystem.getClip();
            centDie.open(audioIn);
            
            // shroomHit        
            url = this.getClass().getClassLoader().getResource("shroomHit.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            shroomHit = AudioSystem.getClip();
            shroomHit.open(audioIn);
            
            // newGame        
            url = this.getClass().getClassLoader().getResource("newGame.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            newGame = AudioSystem.getClip();
            newGame.open(audioIn);
            
            // nextLevel        
            url = this.getClass().getClassLoader().getResource("nextLevel.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            nextLevel = AudioSystem.getClip();
            nextLevel.open(audioIn);
            
            // gameOver        
            url = this.getClass().getClassLoader().getResource("gameOver.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            gameOver = AudioSystem.getClip();
            gameOver.open(audioIn);
        }catch(Exception e){}
    }
    
    /**
     * Play cannon sound
     **/
    public void cannon(){
        cannon.stop();
        cannon.setFramePosition(0);
        cannon.start();
    }
    
    /**
     * Play laser sound
     **/
    public void laser(){
        laser.stop();
        laser.setFramePosition(0);
        laser.start();
    }
    
    /**
     * Play ship explode sound
     **/
    public void shipExplode(){
        shipExplode.stop();
        shipExplode.setFramePosition(0);
        shipExplode.start();
    }
    
    /**
     * Play centipede die sound
     **/
    public void centDie(){
        centDie.stop();
        centDie.setFramePosition(0);
        centDie.start();
    }
    
    /**
     * Play mushroom hit sound
     **/
    public void shroomHit(){
        shroomHit.stop();
        shroomHit.setFramePosition(0);
        shroomHit.start();
    }
    
    /**
     * Play new game sound
     **/
    public void newGame(){
        newGame.stop();
        newGame.setFramePosition(0);
        newGame.start();
    }
    
    /**
     * Play next level sound
     **/
    public void nextLevel(){
        nextLevel.stop();
        nextLevel.setFramePosition(0);
        nextLevel.start();
    }
    
    /**
     * Play game over sound
     **/
    public void gameOver(){
        gameOver.stop();
        gameOver.setFramePosition(0);
        gameOver.start();
    }
}