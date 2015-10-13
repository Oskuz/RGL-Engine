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
public interface ArtificialIntelligenceInterface {
    
    /**
     * called when is Creature's turn, takes params creture.
     * @param creature
     * @return 
     * @throws com.oskari.rgl.engine.EngineException
     */
    public int update(Creature creature) throws EngineException;

    /**
     *
     * @return
     */
    public String name();
    
}
