package com.oskari.rgl.engine;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oskari
 */
public class File {
    
   static public ArrayList<String> Tokens(String s) {
       int i = 0;
      char[] chars = s.toCharArray();
      String a = new String();
      ArrayList<String> tokens = new ArrayList<>();
       while(true){
           if(i == chars.length) break;
           if( chars[i] == ' ' || chars[i] == '\n' ){
               i++; 
               a.replaceAll("\\s","");
               if(a.length() != 0){
                   tokens.add(a);
                   a = new String();
               }
               continue;
           }
           if( chars[i] == '[' || chars[i] == ']' || chars[i] == ',' ){ 
               a.replaceAll("\\s","");
               if(a.length() != 0){
                   tokens.add(a);
                   a = new String();
               }
               tokens.add(Character.toString(chars[i]).replaceAll("\\s",""));
               i++;
               continue;
           }
           a += chars[i];
           i++;
       }
       return tokens;
   }
    
}
