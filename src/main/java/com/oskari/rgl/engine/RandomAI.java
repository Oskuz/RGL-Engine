/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskari.rgl.engine;

/**
 *
 * @author oskari
 */
public class RandomAI implements ArtificialIntelligenceInterface {

    private int last = -1;
    @Override
    public int update(Creature creature) throws EngineException {

        try{
            if(last != 1 && Engine.maps.get(creature.getMap()).getTile(creature.getX(), creature.getY()-1).canAccess()){
                creature.move(creature.getX(), creature.getY()-1);
                last = 0;
                return 1;
            }
            if(last != 0 && Engine.maps.get(creature.getMap()).getTile(creature.getX(), creature.getY()+1).canAccess()){
                creature.move(creature.getX(), creature.getY()+1);
                last = 1;
                return 1;
            }
            if(last != 2 && Engine.maps.get(creature.getMap()).getTile(creature.getX()+1, creature.getY()).canAccess()){
               creature.move(creature.getX()+1, creature.getY());
               last = 3;
               return 1;
            } 
             if(last != 3 && Engine.maps.get(creature.getMap()).getTile(creature.getX()-1, creature.getY()).canAccess()){
                 creature.move(creature.getX()-1, creature.getY());
                 last = 2;
                 return 1;
             }
             
        } catch(Exception e){
            
        }
        last = -1;
        throw new EngineException();
    }

    @Override
    public String name() {
        return "RandomAI";
    }
    
}
