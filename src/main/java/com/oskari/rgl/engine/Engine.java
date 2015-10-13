package com.oskari.rgl.engine;

import java.util.ArrayList;
import java.util.HashMap;

public class Engine {
    
    /**
     * include reference to evry creature.
     */
    public static ArrayList<Creature> creatures = new ArrayList<>();
    public static HashMap<String,Map> maps = new HashMap<>();
    
    public static void update(){
        creatures.stream().forEach((c) -> {
            c.update();
        });
    }
}

