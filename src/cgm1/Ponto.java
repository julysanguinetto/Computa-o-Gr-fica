/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgm1;

/**
 *
 * @author Sanguinetto
 */
public class Ponto {
    
    private float x;
    private float y;
    
    public Ponto(){}
    
    public Ponto(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
}
