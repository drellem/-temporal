

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.event;

import temporal.util.Tuple;

/**
 *
 * @author drellem
 */
public class Event extends Tuple<String,Object> {

    public Event(String x, Object y) {
        super(x, y);
    }
    
    public String getId(){ return super.x; }
    public Object getMeta(){ return super.y; }
    
}
