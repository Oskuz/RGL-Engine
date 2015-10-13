package com.oskari.rgl.engine;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by oskari on 8.10.2015.
 */
public class Creature extends GameObject {

    private String map;
    private String name;
    private String lastName;
    private int x, y;
    private ArtificialIntelligenceInterface Ai;

    public Creature(String name, String lastName, int x, int y, String map){
        this.x=x;
        this.y=y;
        this.map = map;
        this.lastName = lastName;
        this.name = name;
    }
    
    public Creature(String file){
        try {
            this.parseCreature(File.Tokens(file));
        } catch (EngineException ex) {
            Logger.getLogger(Creature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Creature(ArrayList<String> tokens){
        try {
            parseCreature(tokens);
        } catch (EngineException ex) {
            Logger.getLogger(Creature.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    private void parseCreature(ArrayList<String> tokens) throws EngineException{
        System.out.println(tokens);
        if(tokens.size()  < 1 ){
           throw new EngineException("too smal map");
       }
       if(!"CREATURE".equals(tokens.get(0))){
           throw new EngineException("Wrong type");
       }
       name = tokens.get(2);
       lastName = tokens.get(4);
       map = tokens.get(6);
       x = Integer.parseInt(tokens.get(8));
       y = Integer.parseInt(tokens.get(10));
       Ai = Engine.aiList.get(tokens.get(12));
    }

    @Override
    public String toString() {
        return "CREATURE ["+name+","+lastName + "," + map + "," + x + "," + y + "," + Ai.name() + ']';
    }
    
}
