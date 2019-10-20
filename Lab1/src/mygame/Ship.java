/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.NativeLibraryLoader;
import com.jme3.texture.Texture;
/**
 *
 * @author Dell-admin
 */
public class Ship {
    
    final String MATERIAL1 = "Common/MatDefs/Misc/Unshaded.j3md"; 
    final String TEXTUREKEY1 = "Textures/Terrain/Rock/Rock.PNG";
    
    public static Sphere sphere;
    private RigidBodyControl shipPhy;
    
    private Geometry shipGeo;
   
    
    public Ship(){
        
    }
    
     
    public void createBall(AssetManager assetManager, boolean flag ){
        
        /** Initialize the cannon ball geometry */
        sphere = new Sphere(52, 52, 4f, true, false);
        sphere.setTextureMode(Sphere.TextureMode.Projected);
        
        /** Create a cannon ball geometry and attach to scene graph. */
        shipGeo = new Geometry("cannon ball", sphere);
        Material stoneMat;
        if (flag) 
            stoneMat = new Material(assetManager, MATERIAL1);
        else
            stoneMat = assetManager.loadMaterial(MATERIAL1);
        TextureKey key2 = new TextureKey(TEXTUREKEY1);
        key2.setGenerateMips(true);
        Texture tex2 = assetManager.loadTexture(key2);
        if (flag)
            stoneMat.setTexture("ColorMap", tex2);
        shipGeo.setMaterial(stoneMat); 
        
        /** Position the cannon ball  */
        shipGeo.setLocalTranslation(new Vector3f(50, -30, -40));
    }
    
    public Geometry getGeometry(){
        return shipGeo;
    }
    
    public RigidBodyControl getPhysics(){
        return shipPhy;
    }
    
    public void createPhysic(BulletAppState bulletAppState, boolean flag){
        if (NativeLibraryLoader.isUsingNativeBullet()) {
            NativeLibraryLoader.loadNativeLibrary("bulletjme", true);
}
        /** Make the ball physcial with a mass > 0.0f */
         shipPhy = new RigidBodyControl(1f);
        
        
        /** Add physical ball to physics space. */
        shipGeo.addControl(shipPhy);
        if (flag)
            bulletAppState.getPhysicsSpace().add(shipPhy);
        shipPhy.setGravity(new Vector3f(0,0,0));
        
        /** Accelerate the physcial ball to shoot it. */
        shipPhy.setLinearVelocity(new Vector3f(-7, -3, 5));
    }
    
    public Vector3f getLocation(){
        return shipPhy.getPhysicsLocation();
    }
    
    public void delete(){
        shipGeo.removeFromParent();
    }
    
}
