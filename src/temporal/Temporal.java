/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import temporal.entity.Entity;
import temporal.entity.NPC;
import temporal.entity.PhysicalComponent;
import temporal.entity.Updatable;
import temporal.map.Map;
import temporal.map.MapEdge;
import temporal.parser.*;
/**
 * This class is the Main Class and stores the date and time.
 * @author drellem
 */
public class Temporal { //TODO Enable talking to entities
    
    public static final Temporal temporal = new Temporal();
    
    public static Time nullTime = Time.nullTime;
    
    
    
    private Time time = new Time(1914,12,1,9,0);
    private HashMap<String, Command> commandBindings = new HashMap<>();
    private Command defaultCommand = lexer -> {
        System.out.println("Command not found\n");
        return new Time();
    };
    private ArrayList<String> commands = new ArrayList<>();
    
    public void init(){
        ///Initialize Map
        Map.setDefaultMap();
        
        //Initialize Commands
        commands.add("HELP");
        commands.add("QUIT");
        commands.add("TIME");
        commandBindings.put("HELP", l -> {
            String s = "Commands are:";
            for(String c : commands){
                s += "\n" + c;
            }
            System.out.println(s+"\n");
            return new Time();
        });
        commandBindings.put("QUIT", l -> {
            System.exit(0);
            return new Time();
                });
        commandBindings.put("TIME", l -> {
            System.out.println(this.time.toString()+"\n");
            return new Time();
        });
        commandBindings.put("PEOPLE", l -> {
            Set<Integer> npcs = Entity.allEntitiesWithComponent(NPC.class);
            //System.out.println(npcs.size());
            System.out.println("People in " + Map.getCurrentNode().getMeta().getId() + ":");
            npcs = Entity.allNPCsInCurrentLocation();
            for(Integer i : npcs){
                System.out.println("\t" + Entity.getComponent(i, NPC.class).getName());
            }
            /*for(Integer i : npcs){
                NPC n = Entity.getComponent(i, NPC.class);
                //System.out.println(n.getName());
                if(n.getLocation().equals(Map.getCurrentNode().getMeta())){
                    System.out.println("\t" + n.getName());
                }
                
                
              
            }*/
            System.out.println("");
                
            return new Time();
        });
        commandBindings.put("LOCATION", l -> {
            System.out.println(Map.getCurrentNode().getMeta().getId()+"\n");
            return new Time();
        });
        commandBindings.put("GOTO", l ->{
            String newLocus = l.next().getMeta();
            newLocus = newLocus.trim().toUpperCase();
            MapEdge e = Map.isAdjacent(newLocus);
            if(e==null){
                System.out.println("Location not adjacent to current location\n");
            }
            Time t = new Time();
            if(e!=null){
                t = Map.go(newLocus, e);
            }
            return t;
        });
        commandBindings.put("TELL", l ->{
            String npc = l.next().getMeta();
            npc = npc.trim().toUpperCase();
            
            return Time.deltaMinute;
        });
        //Initialize entities
        Integer i = Entity.makeEntity();
        Entity.addComponent(i, new NPC(Map.bar, "Fred"));
    }
    
    public void gameLoop(){
        System.out.print(">");
        boolean resume = true;
        String in;
        StringStream stream;
        Lexer lexer;
        Time delta; //change in time after a command
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        while(resume){
            try {
                
                //Read and execute commands, time is dealt with in the exec process
                in = reader.readLine().toUpperCase();
                if(!commands.contains(in)&&commandBindings.containsKey(in))commands.add(in);
                in += "\n";
                System.out.println("");
                stream = new StringStream(in.toCharArray());
                lexer = new Lexer(stream);
                String s = lexer.next().getMeta();
                if(!commands.contains(s))commands.add(s);
                delta = commandBindings.getOrDefault(s, this.defaultCommand).exec(lexer);
                time.add(delta);
                
                //Update all updatables
                Set<Integer> updatables = Entity.allEntitiesWithComponent(Updatable.class);
                for(Integer u : updatables){
                    Entity.getComponent(u, Updatable.class).update(delta);
                }
                System.out.print(">");
                
            } catch (IOException ex) {
                Logger.getLogger(Temporal.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("IO Exception while reading input");
            }
            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Temporal game = new Temporal();
        System.out.println("Type 'help' to view commands\n");
        game.init();
        game.gameLoop();
    }
    
}
