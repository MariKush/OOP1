package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */

public class Game extends SimpleApplication {

    public static RigidBodyControl torpedoPhy;
    public static RigidBodyControl ballPhy;
    
    public static Geometry shipGeo;
    public static Geometry torpedoGeo;
   
    public static Sphere sphere;
    
    /** Prepare the Physics Application State (jBullet) */
    private BulletAppState bulletAppState;
    boolean wasShoot = false;
    static boolean wasHit = false;
    
    
    
    @Override
    public void simpleInitApp() {
              
        /** Set up Physics Game */
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        Ship.createShip(assetManager, rootNode, bulletAppState);
        
        flyCam.setMoveSpeed(25);
        
        // You can map one or several inputs to one named action
        inputManager.addMapping("Shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Shoot");
      
    }
    
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if(!wasShoot)
                if (name.equals("Shoot") && !keyPressed) {
                    Torpedo.createTorpedo(assetManager, rootNode, bulletAppState, cam);
                    wasShoot=true;
                }
        }
    };
    
    
    static void hitСheck(Vector3f bf, Vector3f tf){
        
        if(bf.subtract(tf).length()<5f){
            wasHit = true;
            
            shipGeo.removeFromParent();
            torpedoGeo.removeFromParent();               
        }
    }
        

    
    @Override
    public void simpleUpdate(float tpf) {
        if(!wasShoot)return;
        if (wasHit) return;
         
        torpedoPhy.setLinearVelocity(Physics.following(ballPhy.getPhysicsLocation(), torpedoPhy.getPhysicsLocation()));
        
       hitСheck(ballPhy.getPhysicsLocation(), torpedoPhy.getPhysicsLocation());
          
    }
    
}