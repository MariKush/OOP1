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
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.system.NativeLibraryLoader;

/**
 *
 * @author Dell-admin
 */
public class Torpedo {
        
    final String MATERIAL1 = "Common/MatDefs/Misc/Unshaded.j3md"; 
    final String TEXTUREKEY1 = "Textures/Terrain/Rock/Rock.PNG";
    
    public static Sphere sphere;  
    private RigidBodyControl torpedoPhy;
    
    private Geometry torpedoGeo;
    
    Torpedo() {
         
    };

 
    public void createBall(AssetManager assetManager, Vector3f camLocation, boolean flag){

        
        /** Initialize the cannon ball geometry */
        sphere = new Sphere(52, 52, 0.4f, true, false);
        sphere.setTextureMode(Sphere.TextureMode.Projected);
        
        /* Create a cannon ball geometry and attach to scene graph. */
        torpedoGeo = new Geometry("cannon ball", sphere);
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
        
        torpedoGeo.setMaterial(stoneMat);
        
        
        /** Position the cannon ball  */
        torpedoGeo.setLocalTranslation(camLocation);
    }
    
    public Geometry getGeometry(){
        return torpedoGeo;
    }
    
    public RigidBodyControl getPhysics(){
        return torpedoPhy;
    }
    
    public void createPhysic(BulletAppState bulletAppState, boolean flag){
        if (NativeLibraryLoader.isUsingNativeBullet()) {
            NativeLibraryLoader.loadNativeLibrary("bulletjme", true);
        }
        /** Make the ball physcial with a mass > 0.0f */
        torpedoPhy = new RigidBodyControl(1f);
        
                
        /** Add physical ball to physics space. */
        torpedoGeo.addControl(torpedoPhy);
        if (flag)
            bulletAppState.getPhysicsSpace().add(torpedoPhy);
        torpedoPhy.setGravity(new Vector3f(0,0,0));
    }
    
    
   
    
    
    public Vector3f getLocation(){
        
        return torpedoPhy.getPhysicsLocation();
        
    }
    
  
   public void changeDirection(Vector3f shipV){
       torpedoPhy.setLinearVelocity(Physics.following(shipV, torpedoPhy.getPhysicsLocation()));
   }
   
   public void delete(){
       torpedoGeo.removeFromParent();
   }
    
}
