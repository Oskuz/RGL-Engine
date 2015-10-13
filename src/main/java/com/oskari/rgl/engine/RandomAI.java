/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskari.rgl.engine;

import java.util.Random;

/**
 *
 * @author oskari
 */
public class RandomAI implements ArtificialIntelligenceInterface {

    @Override
    public void update(Creature creature) {

        try{
            
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX()-1, creature.getY()-1).canAccess()){
                creature.move(creature.getX()-1, creature.getY()-1);
                return;
            }
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX()-1, creature.getY()+1).canAccess()){
                creature.move(creature.getX()-1, creature.getY()+1);
                return;
            }
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX()+1, creature.getY()-1).canAccess()){
               creature.move(creature.getX()+1, creature.getY()-1);
               return;
            } 
             if(Engine.maps.get(creature.getMap()).getTile(creature.getX()+1, creature.getY()+1).canAccess()){
                 creature.move(creature.getX()+1, creature.getY()+1);
                 
             }
             
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return "RandomAI";
    }
    
}
