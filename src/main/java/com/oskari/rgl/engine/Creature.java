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
    private int action_points;
    private int speed;
    public boolean turn = true; //help with player

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAction_points() {
        return action_points;
    }

    public void setAction_points(int action_points) {
        this.action_points = action_points;
    }

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

    public void move(int x, int y) throws EngineException{
        if( x < 0 || x > Engine.maps.get(map).getSizeX() || y < 0 ||y > Engine.maps.get(map).getSizeY() ) throw new EngineException("move point is out from area");
        
        if(Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2)) <= 1 && Engine.maps.get(map).getTile(x, y).canAccess()){
            Engine.maps.get(map).getTile(this.x, this.y).eraseCreature();
            Engine.maps.get(map).getTile(x, y).setCreature(this);
            this.x = x; this.y=y;
        }
        else {
            throw new EngineException("can't move: from"+this.x+", ("+this.y+")("+x+", "+ y +")");
        }
        
    }

    public String getMap() {
        return map;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    public void update() {

    }

    public void setAi(ArtificialIntelligenceInterface Ai) {
        this.Ai = Ai;
    }
    
    private void parseCreature(ArrayList<String> tokens) throws EngineException{
        System.out.println("parseCreature" + tokens);
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
       if(Engine.aiList.get(tokens.get(12)) == null) throw new EngineException("Ai not found");
       Ai = Engine.aiList.get(tokens.get(12));
       speed = Integer.parseInt(tokens.get(14));
       action_points = Integer.parseInt(tokens.get(16));
    }

    @Override
    public String toString() {
        return "CREATURE ["+name+","+lastName + "," + map + "," + x + "," + y + "," + Ai.name() + ']';
    }
    
    public String getFirstName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getName(){
        return name + " " + lastName;
    }

    public int takeTurn() throws EngineException{
        if(Ai == null) return 0;
        return Ai.update(this);
    }
    
}
