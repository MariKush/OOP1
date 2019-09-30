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
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import static mygame.Game.sphere;
import static mygame.Game.torpedoGeo;

/**
 *
 * @author Dell-admin
 */
public class Torpedo {
    
    static protected void createTorpedo(AssetManager assetManager, Node rootNode,
        BulletAppState bulletAppState, Camera cam){
        
        /** Initialize the cannon ball geometry */
        sphere = new Sphere(52, 52, 0.4f, true, false);
        sphere.setTextureMode(Sphere.TextureMode.Projected);
        
        /** Create a cannon ball geometry and attach to scene graph. */
        torpedoGeo = new Geometry("cannon ball", sphere);
        Material stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
        key2.setGenerateMips(true);
        Texture tex2 = assetManager.loadTexture(key2);
        stone_mat.setTexture("ColorMap", tex2);
        
        
        torpedoGeo.setMaterial(stone_mat);
        rootNode.attachChild(torpedoGeo); 
        
        /** Position the cannon ball  */
        torpedoGeo.setLocalTranslation(cam.getLocation());
        
        /** Make the ball physcial with a mass > 0.0f */
        Game.torpedoPhy = new RigidBodyControl(1f);
        
                
        /** Add physical ball to physics space. */
        torpedoGeo.addControl(Game.torpedoPhy);
        bulletAppState.getPhysicsSpace().add(Game.torpedoPhy);
        Game.torpedoPhy.setGravity(new Vector3f(0,0,0));
        
        
    }
   
}