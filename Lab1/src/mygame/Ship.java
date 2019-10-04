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
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
/**
 *
 * @author Dell-admin
 */
public class Ship {
    
    
    private RigidBodyControl shipPhy;
    
    private Geometry shipGeo;
    
    public Ship(AssetManager assetManager, Node rootNode,
        BulletAppState bulletAppState ){
        
        /** Initialize the cannon ball geometry */
        Game.sphere = new Sphere(52, 52, 4f, true, false);
        Game.sphere.setTextureMode(Sphere.TextureMode.Projected);
        
        
        /** Create a cannon ball geometry and attach to scene graph. */
        shipGeo = new Geometry("cannon ball", Game.sphere);
        Material stoneMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
        key2.setGenerateMips(true);
        Texture tex2 = assetManager.loadTexture(key2);
        stoneMat.setTexture("ColorMap", tex2);
        
        
        shipGeo.setMaterial(stoneMat);
        rootNode.attachChild(shipGeo); 
        
        /** Position the cannon ball  */
        shipGeo.setLocalTranslation(new Vector3f(50, -30, -40));
        
        /** Make the ball physcial with a mass > 0.0f */
        shipPhy = new RigidBodyControl(1f);
         
        /** Add physical ball to physics space. */
        shipGeo.addControl(shipPhy);
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
