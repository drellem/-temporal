/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.map;

import temporal.parser.Command;

/**
 * Represents a location in the world.
 * @author drellem
 */
public final class Location { 
    private String id;
    private Command command;
    
    public Location(String id, Command command){
        this.id = id;
        this.command = command;
    }
    
    public String getId(){ return this.id; }
    public Command getCommand(){ return this.command; }
    
    @Override
    public boolean equals(Object o){
            if(o instanceof Location){
                Location l = (Location)o;
                return l.getId().equals(this.id);
            }
            return false;
    }
}
