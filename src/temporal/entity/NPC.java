/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.entity;

import temporal.map.Location;

/**
 *
 * @author drellem
 */
public class NPC extends PhysicalComponent {
    private String name;
    
    public NPC(Location l, String name){
        super(l);
        this.name = name;
    }
    
    public String getName(){ return this.name; }
}
