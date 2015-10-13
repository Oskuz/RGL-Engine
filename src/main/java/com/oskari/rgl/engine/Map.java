/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskari.rgl.engine;

import java.util.ArrayList;
import com.oskari.rgl.engine.EngineException;

/**
 *
 * @author oskari
 */
public class Map {
    private ArrayList<Tile> tiles;
    private int sizeX, sizeY;
    private String name;

    public Map(String file) {
        tiles = new ArrayList<>();
        try{
            loadMap(File.Tokens(file));
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public Map(ArrayList<String> tokens) {
        tiles = new ArrayList<>();
        try{
            loadMap(tokens);
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    
    public Tile getTile(int x, int y){
        return tiles.get(x*sizeX + y);
    }
    @Override
    public String toString() {
        String m = "MAP ["+name+",\n"+sizeX+","+sizeY+",\n";
        m = tiles.stream().map((t) -> (t.toString() + ",")).reduce(m, String::concat);
        m += "\n]";
        return m;
    }
    
    private void loadMap(ArrayList<String> tokens) throws EngineException {
        System.out.println(tokens.toString());
       if(tokens.size()  < 1 ){
           throw new EngineException("too smal map");
       }
       if(!"MAP".equals(tokens.get(0))){
           throw new EngineException("Wrong type");
       }
       int brackets = 0;
       if("[".equals(tokens.get(1))){
           brackets++;
       }
       else {
           throw new EngineException("syntax error");
       }
       this.name = tokens.get(2);
       this.sizeX = Integer.parseInt(tokens.get(4));
       this.sizeY = Integer.parseInt(tokens.get(6));
       int i = 8;
       
       while("TILE".equals(tokens.get(i))){
           ArrayList<String> tile = new ArrayList<>();
            tile.add(tokens.get(i));
            i++;
            tile.add(tokens.get(i));
            i++;
            brackets++;
            
            while(brackets > 1){
                tile.add(tokens.get(i));
                if("[".equals(tokens.get(i))) brackets++;
                if("]".equals(tokens.get(i))) brackets--;
                i++;
            }
            i++; //,
            this.tiles.add(new Tile(tile));
           
       }
    }

    public String getName() {
        return name;
    }

    
}
