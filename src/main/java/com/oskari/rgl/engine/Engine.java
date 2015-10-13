package com.oskari.rgl.engine;

import java.util.ArrayList;
import java.util.HashMap;

public class Engine {
    
    /**
     * include reference to every creature.
     */
    public static ArrayList<Creature> creatures;
    public static HashMap<String,Map> maps;
    public static HashMap<String,ArtificialIntelligenceInterface> aiList;
    
    public static void init(){
        creatures = new ArrayList<>();
        maps = new HashMap<>();
        aiList = new HashMap<>();
        
        aiList.put("RandomAI", new RandomAI());
    }
    
    public static void update(){
        creatures.stream().forEach((c) -> {
            c.update();
        });
    }

    public static void newAi(String name, ArtificialIntelligenceInterface ai) {
        Engine.aiList.put(name, ai);
    }
    
}

