package com.oskari.rgl.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Engine {
    
    /**
     * include reference to every creature.
     */
    public static ArrayList<Creature> creatures;
    public static HashMap<String,Map> maps;
    public static HashMap<String,ArtificialIntelligenceInterface> aiList;
    private static LinkedList<Creature> time_travellers;
    
    public static void init(){
        creatures = new ArrayList<>();
        maps = new HashMap<>();
        aiList = new HashMap<>();
        time_travellers = new LinkedList<>();
        
        aiList.put("RandomAI", new RandomAI());
    }
    
    public static void update(){
        creatures.stream().forEach((c) -> {
            c.update();
        });
        if(time_travellers.size() > 0){
            if(creatures.get(0).turn)time_travellers.addFirst(time_travellers.removeLast());
            Creature c = time_travellers.getFirst();
            if(creatures.get(0).turn) c.setAction_points(c.getAction_points() + c.getSpeed());
            
            while(c.getAction_points() > 0){
               try{
                c.setAction_points(c.getAction_points() - c.takeTurn());
               }catch(Exception e){
                   break;
               }
            }
        }
    }

    public static void newAi(String name, ArtificialIntelligenceInterface ai) {
        Engine.aiList.put(name, ai);
    }
    
    public static void register(Creature c){
        creatures.add(c);
        time_travellers.add(c);
    }
    
}

