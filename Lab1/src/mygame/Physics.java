/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author Dell-admin
 */
public class Physics {
    
    static final float SPEED = 20f;
    
   
    
    static Vector3f following(Vector3f bf, Vector3f tf){
        
        Vector3f delta = bf.subtract(tf);
        
        Vector3f delta_norm = delta.normalize();

        return delta_norm.mult(SPEED);
    }
    
}
