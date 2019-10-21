/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell-admin
 */
public class GameTest {
    
    public GameTest() {
    }
  
  

    /**
     * Test of hitСheck method, of class Game.
     */
    @Test
    public void testHitСheck() {
        System.out.println("hit\u0421heck");
        Vector3f bf = new Vector3f(0, 5, 3);
        Vector3f tf = new Vector3f(2, 10, 1);
        Game instance = new Game();
        instance.hitСheck(bf, tf,false);
        assertFalse(instance.wasHit);
        tf = new Vector3f(2, 6, 1);
        
        instance.hitСheck(bf, tf,false);
        assertTrue(instance.wasHit);
    }

  
}
