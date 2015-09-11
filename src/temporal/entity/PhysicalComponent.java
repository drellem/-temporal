/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.entity;

import temporal.map.Location;

/**
 * The component for physical entities which must occupy a location.
 * @author drellem
 */
public class PhysicalComponent extends Component{
    private Location location;
    
    public PhysicalComponent(Location location){
        this.location = location;
    }
    
    public Location getLocation(){ return this.location; }
    public void setLocation(Location location){ this.location = location; }
}
