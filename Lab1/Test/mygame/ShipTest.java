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
public class ShipTest {
    
    
    @Mock
    private BulletAppState bulletAppState;
    @Mock 
    private AssetManager assetManager;
    
    @Mock
    private Ship s;
    
    public ShipTest() {
        
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

    @Test
    public void createBall() {
        s=new Ship();
        s.createBall(assetManager, false);
        assertNotEquals(s.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(50, -30, -40), s.getGeometry().getLocalTranslation());//position is right
    }
    
    @Test
    public void createPhysic() {
        s=new Ship();
        s.createBall(assetManager, false);
        s.createPhysic(bulletAppState, false);
        
        assertNotEquals(null, s.getPhysics());
        assertNotEquals(s.getGeometry(), null);
        assertEquals(new Vector3f(50, -30, -40), s.getGeometry().getLocalTranslation());
        
    }
      
    
}
