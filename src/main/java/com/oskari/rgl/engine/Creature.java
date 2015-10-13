package com.oskari.rgl.engine;

import java.util.logging.Logger;

/**
 * Created by oskari on 8.10.2015.
 */
public class Creature extends GameObject {

    private String map;
    private int x, y;
    private ArtificialIntelligenceInterface Ai;

    public Creature(int x, int y, String map){
        this.x=x;
        this.y=y;
        this.map = map;
    }

    public void move(int x, int y){
        if(Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2)) <= 1 && Engine.maps.get(map).getTile(x, y).canAccess()){
            Engine.maps.get(map).getTile(this.x, this.y).eraseCreature();
            Engine.maps.get(map).getTile(x, y).setCreature(this);
            this.x = x; this.y=y;
        }
        else {
            System.out.println("Kohtaan ei voi liikkua");
        }
        
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    public void update() {
        Ai.update(this);
    }

    public void setAi(ArtificialIntelligenceInterface Ai) {
        this.Ai = Ai;
    }
}
