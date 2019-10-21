/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.texture.Texture2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.texture.Texture;


import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Dell-admin
 */

public class TorpedoTest {
    
    @Mock
    private BulletAppState bulletAppState;
    @Mock 
    private AssetManager assetManager;
    
    @Mock
    private Torpedo t;
    
    
    public TorpedoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(assetManager.loadMaterial("Common/MatDefs/Misc/Unshaded.j3md")).thenReturn(new Material());
        when(assetManager.loadTexture("Textures/Terrain/Rock/Rock.PNG")).thenReturn(new Texture2D());
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of changeDirection method, of class Torpedo.
     */
    @Test
    public void testChangeDirection() {
                       
        
    }/**/
    
    
    @Test
    public void createBall() {
        t=new Torpedo();
 
        t.createBall(assetManager, new Vector3f(1, 2, 3), false);
        assertNotEquals(t.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(1,2,3), t.getGeometry().getLocalTranslation());//position is right
    }
    
    @Test
    public void createPhysic() {
        t=new Torpedo();
        t.createBall(assetManager,  new Vector3f(1, 2, 3), false);
        t.createPhysic(bulletAppState, false);
        
        assertNotEquals(null, t.getPhysics());
        assertNotEquals(t.getGeometry(), null);
        assertEquals(new Vector3f(1,2,3), t.getGeometry().getLocalTranslation());
        
    }

    
    
}
