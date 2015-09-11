/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author drellem
 */
public class EventManager {
    private HashMap<String,ArrayList<EventObserver>> observers;
    private static EventManager eventManager = new EventManager();
    
    public static EventManager instance(){ return eventManager; }
    
    public EventManager(){
        this.observers = new HashMap<>();
    }
    
    public void dispatch(Event e){
        if(observers.keySet().contains(e.getId())){
            for(EventObserver observer : observers.get(e.getId()))
                observer.observe(e);
        }
    }
    
    public void addObserver(String id, EventObserver o){
        if(observers.keySet().contains(id))
            observers.get(id).add(o);
        else {
            ArrayList<EventObserver> t = new ArrayList<>();
            t.add(o);
            observers.put(id, t);
        }
    }
    
    
}
