package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Sphere;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */

public class Game extends SimpleApplication {
    private Torpedo torpedo;
    private Ship ship;

    
   
    /** Prepare the Physics Application State (jBullet) */
    private BulletAppState bulletAppState;
    boolean wasShoot = false;
    boolean wasHit = false;
    
    
    
    @Override
    public void simpleInitApp() {
              
        /** Set up Physics Game */
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        ship=new Ship();
        
        ship.createBall(assetManager, true);
        ship.createPhysic(bulletAppState, true);
        
        rootNode.attachChild(ship.getGeometry());

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
                    torpedo=new Torpedo();
                    torpedo.createBall(assetManager, cam.getLocation(), true);
                    torpedo.createPhysic(bulletAppState, true);
                    rootNode.attachChild(torpedo.getGeometry());
                    wasShoot=true;
                }
        }
    };
    
    
    void hitСheck(Vector3f bf, Vector3f tf, boolean flag){
              
        //make torpedo and ship invisible
        if(bf.subtract(tf).length()<5f){
            wasHit = true;
            if (!flag) return;
            ship.delete();
            torpedo.delete();
        }
    }
        

    
    @Override
    public void simpleUpdate(float tpf) {
        if(!wasShoot)return;
        if (wasHit) return;
         
        torpedo.changeDirection(ship.getLocation());//todo
        
        hitСheck(ship.getLocation(), torpedo.getLocation(),true);
          
    }
    
}