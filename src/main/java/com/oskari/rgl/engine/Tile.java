/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskari.rgl.engine;

import java.util.ArrayList;

/**
 *
 * @author oskari
 */
public class Tile {
    private Creature creature;
    private int type;
    private boolean access;
    private boolean destroyable;
    private int destroy;
    private int friction;

    public int getFriction() {
        return friction;
    }


    public Tile(int type, boolean access, boolean destroyable, int destroy, int friction) {
        this.creature = null;
        this.type = type;
        this.access = access;
        this.destroyable = destroyable;
        this.destroy = destroy;
        this.friction = friction;
    }

    public boolean isAccess() {
        return access;
    }

    public int getDestroy() {
        return destroy;
    }
    
    public Tile (String file) {
        try{
        loadTile(File.Tokens(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Tile (ArrayList<String> tokens) {
        try{
        loadTile(tokens);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
     
            this.creature = creature; 
        
    }
    
    public boolean canAccess() {
        if(this.creature != null) return false;
        return access;
    }
    
    public void eraseCreature(){
        this.creature = null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public boolean isDestroyable() {
        return destroyable;
    }
    
    public void destroy(){
        if(destroyable){
            this.destroy--;
            
            if(destroy == 0){
                type = 1;
                destroyable = false;
                access = true;
            }
        }
    }

    @Override
    public String toString() {
        return "TILE [ "+ type + "," + access + "," + destroyable +","+ destroy +","+friction+ ']';
    }
    
    private void loadTile(ArrayList<String> tokens) throws EngineException{
        if(tokens.size()  < 1){
           throw new EngineException("too small for tile");
       }
        if(!"TILE".equals(tokens.get(0))){
            throw new EngineException("Wrong type");
        }
        this.type = Integer.parseInt(tokens.get(2));
        this.access = Boolean.parseBoolean(tokens.get(4));
        this.destroyable = Boolean.parseBoolean(tokens.get(6));
        this.destroy = Integer.parseInt(tokens.get(8));
        this.friction = Integer.parseInt(tokens.get(10));
    }
    
}
