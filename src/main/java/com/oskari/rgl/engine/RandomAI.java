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
    public int update(Creature creature) throws EngineException {

        try{
            System.out.println("randomAI");
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX(), creature.getY()-1).canAccess()){
                creature.move(creature.getX(), creature.getY()-1);
                return 1;
            }
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX(), creature.getY()+1).canAccess()){
                creature.move(creature.getX(), creature.getY()+1);
                return 1;
            }
            if(Engine.maps.get(creature.getMap()).getTile(creature.getX()+1, creature.getY()).canAccess()){
               creature.move(creature.getX()+1, creature.getY());
               return 1;
            } 
             if(Engine.maps.get(creature.getMap()).getTile(creature.getX()-1, creature.getY()).canAccess()){
                 creature.move(creature.getX()-1, creature.getY());
                 return 1;
             }
             
        } catch(Exception e){
            
        }
        throw new EngineException();
    }

    @Override
    public String name() {
        return "RandomAI";
    }
    
}
