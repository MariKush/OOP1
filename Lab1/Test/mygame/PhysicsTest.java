/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import static mygame.Physics.SPEED;
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
public class PhysicsTest {
    
    public PhysicsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of following method, of class Physics.
     */
    @Test
    public void testFollowing() {
        System.out.println("following");
        Vector3f bf = new Vector3f(10, -5, 9);
        Vector3f tf = new Vector3f(1, 7, -2);
        Vector3f expResult = bf.subtract(tf).normalize().mult(SPEED);
        Vector3f result = Physics.following(bf, tf);
        assertEquals(expResult, result);
      
    }
    
}
