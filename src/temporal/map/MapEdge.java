/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.map;

import temporal.Time;
import temporal.util.Tuple;

/**
 *
 * @author drellem
 */
public class MapEdge extends Edge {
    private Time distance;
    
    public MapEdge(Node<Location> a, Node<Location> b, Time distance){
        super(a,b);
        this.distance = distance;
    }
    
    public Tuple<Node<Location>,Time> getNode(String s){
        Location la = (Location)a.getMeta();
        Location lb = (Location)b.getMeta();
        Tuple<Node<Location>,Time> ret = new Tuple<>(null, distance);
        if(la.getId().equals(s))ret.x=a;
        if(lb.getId().equals(s))ret.x=b;
        return ret;
    }
}
